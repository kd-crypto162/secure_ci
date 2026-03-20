## Secure CI Pipeline and Containerisation (Assignment 1)

**Student:** <your name>  
**Module:** <module>  
**Date:** <date>  
**Repository:** <GitHub/GitLab link>

### 1) CI Architecture (diagram)

```mermaid
flowchart LR
  Dev[Developer workstation] -->|commit (pre-commit hook runs Checkstyle)| Git[GitHub/GitLab repo]
  Git -->|PR/MR opened to protected main| Review[1+ reviewer required]
  Review -->|merge| Main[main branch]
  Git -->|CI trigger on PR/MR + main| CI[CI runner]

  subgraph CI[Secure CI Pipeline]
    Lint[Lint: Checkstyle] --> Build[Build: Gradle compile/jar]
    Build --> Test[Test: JUnit + JaCoCo coverage]
    Test --> SAST[SAST: SpotBugs]
    SAST --> Docker[Docker build: multi-stage, distroless, non-root]
  end

  Docker --> Artifact[Reports + artifacts uploaded]
```

### 2) Fail-fast mechanisms (how the pipeline stops early)

- **Protected branches + PR/MR workflow**: blocks direct pushes to `main`, forcing review and CI checks.
- **Stage ordering**: lint runs first; if Checkstyle fails, later stages (tests/SAST/docker) do not run.
- **Security gates**: SpotBugs is configured with `ignoreFailures = false`, so findings fail the job.
- **Build/test gates**: compilation failures or failing unit tests stop immediately; coverage reports are still uploaded using “always” artifacts to aid debugging.
- **Caching**: dependency caches reduce time-to-feedback so failures are detected sooner.

### 3) Container security hardening

The Docker image applies hardening principles:

- **Multi-stage build**: the build stage includes Gradle/JDK tooling; the runtime stage contains only the app JAR.
- **Minimal runtime base**: `distroless/java17-debian12:nonroot` reduces attack surface (no shell, fewer packages).
- **Non-root user**: distroless `:nonroot` runs the app without root privileges.
- **Secrets not baked-in**: no API keys/passwords are stored in the Dockerfile; configuration should be injected via environment variables or a secret manager at runtime.

### 4) Extending to Canary or Blue-Green deployments

- **Blue-Green**: keep two production environments (Blue = live, Green = new). After CI passes and an image is built, deploy to Green, run smoke tests (e.g., `/health`), then switch traffic. Rollback = switch back to Blue.
- **Canary**: deploy the new version to a small percentage of traffic first (e.g., 5%), monitor error rate/latency, then gradually increase if healthy. Rollback = reduce canary traffic to 0%.

Both strategies can be added after the Docker build stage by introducing:

- **Image publishing** (to GitHub Container Registry / GitLab Registry)
- **Deployment stages** (Kubernetes or VM-based) with automated smoke tests and monitoring-based gates

### 5) How Agentic AI could monitor this pipeline in real-time

An “agentic AI” component could:

- **Watch CI logs and metrics**: classify failures (lint vs test vs SAST vs docker), detect recurring patterns, and propose next steps.
- **Monitor security findings**: prioritize SpotBugs findings by severity and historical trend; open issues automatically.
- **Auto-triage flaky tests**: detect non-determinism, suggest quarantine or stabilization.
- **Proactive remediation**: generate pull requests for small safe fixes (formatting, dependency bumps, configuration corrections) while respecting approval rules.

This agent would integrate with the CI system via APIs/webhooks, and operate under strict guardrails (least privilege, audit logging, human approval for changes).

