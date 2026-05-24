# Trabalho Jenkins + JMeter + Grafana

Este projeto sobe Jenkins, InfluxDB e Grafana com Docker. O Jenkins executa o script `galeriamusical.jmx` em modo non-GUI do JMeter. O JMeter envia as metricas para o InfluxDB via Backend Listener usando o protocolo Graphite, conforme a ideia do tutorial da BlazeMeter. O Grafana usa o InfluxDB como datasource e exibe o dashboard `JMeter Galeria Musical`.

## Como subir

No PowerShell, dentro desta pasta:

```powershell
docker compose up -d --build
```

Se aparecer `Acesso negado` no Docker, abra o Docker Desktop, espere ele ficar rodando e tente abrir o PowerShell como Administrador. Tambem confirme em Docker Desktop > Settings > Resources > WSL Integration se a integracao esta ativa, caso esteja usando WSL.

## URLs

- Jenkins: http://localhost:8080
- Grafana: http://localhost:3000
- InfluxDB: http://localhost:8086

Login do Grafana:

- Usuario: `admin`
- Senha: `admin`

## Primeiro acesso ao Jenkins

Pegue a senha inicial:

```powershell
docker exec galeria-jenkins cat /var/jenkins_home/secrets/initialAdminPassword
```

Depois entre em http://localhost:8080, cole a senha, instale os plugins sugeridos e crie seu usuario.

## Criar o job no Jenkins

1. Clique em `New Item`.
2. Nome: `jmeter-galeria-musical`.
3. Tipo: `Pipeline`.
4. Em `Pipeline`, escolha `Pipeline script`.
5. Cole o conteudo do arquivo `Jenkinsfile`.
6. Salve e clique em `Build Now`.

Ao terminar, o Jenkins arquiva:

- `results/galeriamusical-results.jtl`
- `results/html-report`

## Ver o dashboard no Grafana

1. Abra http://localhost:3000.
2. Entre com `admin` / `admin`.
3. Va em Dashboards.
4. Abra `JMeter Galeria Musical`.
5. Rode o job no Jenkins novamente e observe os graficos atualizando.

## O que mostrar no video

1. Fale seu nome no inicio.
2. Mostre o Docker Desktop aberto.
3. Mostre o comando `docker compose up -d --build`.
4. Abra o Jenkins em http://localhost:8080.
5. Mostre o job `jmeter-galeria-musical`.
6. Rode `Build Now`.
7. Abra o console do build e mostre o JMeter executando.
8. Abra o Grafana em http://localhost:3000.
9. Mostre o dashboard com metricas do JMeter.
10. Mostre rapidamente que o script usado e o `galeriamusical.jmx`.
