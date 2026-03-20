## Assignment 1 — Evidence Checklist (Screenshots + Logs)

Use this as your “what to capture” list for the ZIP + report.

### 1) Secure SCM evidence (screenshots)

- **Protected branch** settings for `main` (no direct pushes).
- **Merge/Pull request rule** requiring **at least 1 reviewer**.
- A screenshot showing a PR/MR cannot be merged without review (if your platform supports it).

### 2) CI pipeline evidence (screenshots)

Capture one successful run of your pipeline showing stages/jobs for:

- **Lint/Checkstyle**
- **Build**
- **Test + Coverage** (JaCoCo report artifact)
- **SAST** (SpotBugs report artifact)
- (Optional) **Docker build**

### 3) Container hardening evidence

Show the Dockerfile applies:

- **Multi-stage build**
- **Minimal runtime base** (distroless)
- **Non-root runtime** (`:nonroot`)
- **No secrets baked into image** (no API keys/passwords in Dockerfile)

### 4) Local container start evidence (logs)

Once Docker is installed, run:

```bash
cd assignment-1/app
docker build -t secureci-app:local .
docker run --rm -p 8080:8080 secureci-app:local
```

Screenshot the terminal logs showing:

- `secureci-app started on http://0.0.0.0:8080`
- `healthcheck: http://0.0.0.0:8080/health`

Optional extra evidence:

```bash
curl http://localhost:8080/health
```

Screenshot `ok`.

