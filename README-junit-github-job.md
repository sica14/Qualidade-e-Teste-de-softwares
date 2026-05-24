# Trabalho Jenkins + GitHub + JUnit

Este exemplo atende ao trabalho: um job do Jenkins busca um repositório do GitHub e executa testes JUnit.

## 1. Subir o Jenkins com Maven e Git

Nesta pasta, rode:

```powershell
docker compose up -d --build
```

Abra:

- Jenkins: http://localhost:8080

Se for o primeiro acesso, pegue a senha:

```powershell
docker exec galeria-jenkins cat /var/jenkins_home/secrets/initialAdminPassword
```

## 2. Criar o repositório no GitHub

O projeto que deve ir para o GitHub está em:

```text
junit-simple-repo
```

No GitHub, crie um repositório chamado, por exemplo:

```text
junit-simple-jenkins
```

Depois, no PowerShell:

```powershell
cd .\junit-simple-repo
git init
git add .
git commit -m "Projeto simples com JUnit para Jenkins"
git branch -M main
git remote add origin https://github.com/SEU_USUARIO/junit-simple-jenkins.git
git push -u origin main
```

Troque `SEU_USUARIO` pelo seu usuário do GitHub.

## 3. Criar o job no Jenkins

1. Clique em `New Item`.
2. Nome: `junit-github-job`.
3. Escolha `Pipeline`.
4. Em `Pipeline`, escolha `Pipeline script from SCM`.
5. SCM: `Git`.
6. Repository URL: `https://github.com/SEU_USUARIO/junit-simple-jenkins.git`.
7. Branch: `*/main`.
8. Script Path: `Jenkinsfile`.
9. Salve.
10. Clique em `Build Now`.

Tambem deixei um modelo de configuracao XML do job em:

```text
jenkins-job-junit-github-config.xml
```

Ele serve como referencia caso voce queira importar/criar o job via Jenkins CLI. Troque `SEU_USUARIO` pela sua conta do GitHub antes de usar.

## 4. O que mostrar no video

1. Fale seu nome no inicio.
2. Mostre o repositório no GitHub com `pom.xml`, `Jenkinsfile` e `CalculadoraTest.java`.
3. Mostre o Jenkins aberto.
4. Mostre a configuracao do job apontando para o GitHub.
5. Clique em `Build Now`.
6. Abra o `Console Output` e mostre `mvn clean test`.
7. Mostre o resultado dos testes JUnit no Jenkins.
