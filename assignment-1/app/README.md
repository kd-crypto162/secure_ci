## Assignment 1 App (Java)

This is a small Java 17 application used to demonstrate a **secure CI pipeline**.

### Local prerequisites

- Java 17 (you already have this)
- (Optional for local container run) Docker Desktop

### Run tests locally (no Docker required)

If you have Gradle installed:

```bash
gradle test
gradle jacocoTestReport
gradle check
```

If you do **not** have Gradle installed, the CI pipeline will still run these steps using a Gradle Docker image (GitLab) or a Gradle setup action (GitHub).

### Build and run locally (plain Java)

```bash
gradle run
```

### Container build/run (once Docker is installed)

```bash
docker build -t secureci-app:local .
docker run --rm -p 8080:8080 secureci-app:local
```

Expected log (evidence screenshot): it should print that the server is listening on port 8080.

