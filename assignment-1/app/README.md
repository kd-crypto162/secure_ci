## Assignment 1 App (Java)

This is a small Java 17 application used to demonstrate a **secure CI pipeline** (quality checks, SAST, coverage) and **secure containerisation**.

### Local prerequisites

- Java 17 (you already have this)
- (Optional for local container run) Docker Desktop

### Recommended local verification (no Gradle needed)

If you don’t have Gradle installed locally, Docker is the easiest way to verify, because the `Dockerfile` contains a Gradle builder stage.

### Container build/run (once Docker is installed)

```bash
docker build -t secureci-app:local .
docker run --rm -p 8080:8080 secureci-app:local
```

Expected log (evidence screenshot):
- it should print that the server is listening on port `8080`
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

### Secrets note (required by the assignment)

The Docker image does not hardcode API keys / DB passwords. If you extend this app, inject secrets at runtime using environment variables or a secret manager.

