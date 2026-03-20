## Assignment 1 App (Java)

This is a small Java 17 application used to demonstrate a **secure CI pipeline** (quality checks, SAST, coverage) and **secure containerisation**.

### Local prerequisites


### Container build/run (once Docker is installed)

```bash
docker build -t secureci-app:local .
docker run --rm -p 8080:8080 secureci-app:local
```

Expected log (evidence screenshot):
- prints that the server is listening on port `8080`
- `/health` endpoint URL

Optional quick check (extra evidence):

```bash
curl http://localhost:8080/health
```
Expected response body: `ok`

### Where CI reports are generated

GitHub Actions runs `gradle check`, producing reports under:
- `assignment-1/app/build/reports/checkstyle/`
- `assignment-1/app/build/reports/jacoco/test/html/`
- `assignment-1/app/build/reports/spotbugs/`



