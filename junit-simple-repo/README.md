# JUnit Simple Repo

Projeto Maven simples com JUnit 5 para executar em um job Pipeline do Jenkins.

## Rodar localmente

```bash
mvn clean test
```

## Jenkins

Crie um job `Pipeline` no Jenkins usando `Pipeline script from SCM`, aponte para este repositório GitHub e informe o caminho `Jenkinsfile`.
