## Assignment 1 — Secure CI Pipeline and Containerisation

Goal: build a **secure, automated CI pipeline** for a small Java application that enforces:

- **Code quality** (format/style checks)
- **Security scanning** (SAST)
- **Automated build + test + coverage**
- **Container hardening** best practices

This folder contains:

- `app/` — the Java application (Gradle Wrapper-based; no local Gradle install needed)
- `ci/` — optional notes/templates (screenshots checklist, evidence)
- `report/` — your max-3-page report source (Markdown you can export to PDF)

### What we are building (high level)

Pipeline stages (fail-fast):

1. Lint / style checks (Checkstyle)
2. Build
3. Test + coverage (JUnit + JaCoCo)
4. SAST (SpotBugs)
5. Container build (multi-stage Dockerfile, minimal runtime image, non-root)

### Next

Go to `assignment-1/app/README.md` to run locally (once Docker/Gradle prerequisites are available) and to understand the project structure.

### Secure SCM requirements (how to get marks)

These are configured in the **Git hosting platform UI** (GitHub/GitLab):

- **Protected branch (`main`)**:
  - block direct pushes
  - require PR/MR to merge
  - require status checks (CI) to pass before merge (recommended)
- **Require at least 1 reviewer** for PR/MR merges

### Pre-commit hook (local lint gate)

This repo includes a `pre-commit` hook that runs Checkstyle before a commit (when Gradle is available).

Enable it once per clone:

```powershell
pwsh .\scripts\enable-githooks.ps1
```


