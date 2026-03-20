## Assignment 1 — Secure CI Pipeline and Containerisation

Goal: build a **secure, automated CI pipeline** for a Java application that enforces code quality, security scanning, and container best practices.

- **Code quality** (format/style checks)
- **Security scanning** (SAST)
- **Automated build + test + coverage**
- **Container hardening** best practices

- `app/` — the Java application and build + analysis configuration


### high level

Pipeline stages (fail-fast order):

1. Lint / style checks (Checkstyle)
2. Build
3. Test + coverage (JUnit + JaCoCo)
4. SAST (SpotBugs)
5. Container build (multi-stage Dockerfile, minimal runtime image, non-root)

### Where CI configuration lives

- GitHub Actions workflow: `.github/workflows/assignment-1-ci.yml`
- Dockerfile: `app/Dockerfile`


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

