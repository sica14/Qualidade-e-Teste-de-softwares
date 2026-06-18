# IBMEC Research Stars - QA

Arquivos de qualidade e testes para a aplicacao IBMEC Research Stars.

Este pacote contem a configuracao para executar:

- Testes de unidade com JUnit.
- Teste funcional com Selenium WebDriver.
- Teste de desempenho com JMeter.
- Pipeline no Jenkins.
- Armazenamento de metricas no InfluxDB.
- Dashboard de desempenho no Grafana.

## Estrutura

```text
Jenkinsfile.qa
docker-compose.qa.yml
pom.xml
frontend/vite.config.ts
src/main/java/br/com/ibmec/researchstars/common/config/CorsConfig.java
qa/
  grafana/
  influxdb/
  jenkins/
  jmeter/
  selenium/
```

## Como rodar

Na raiz da aplicacao IBMEC Research Stars, copie estes arquivos mantendo a estrutura de pastas e execute:

```powershell
docker compose -f docker-compose.qa.yml up -d --build
```

Servicos principais:

```text
Frontend: http://localhost:5173
Backend: http://localhost:8082
Jenkins: http://localhost:8090
Selenium Grid: http://localhost:4444
Selenium noVNC: http://localhost:7900
Grafana: http://localhost:3001
InfluxDB: http://localhost:8086
```

Grafana:

```text
usuario: admin
senha: admin
```

## Jenkins

O container do Jenkins cria automaticamente o job:

```text
ibmec-research-stars-qa
```

Para executar:

1. Abra `http://localhost:8090`.
2. Entre no job `ibmec-research-stars-qa`.
3. Clique em `Build Now`.
4. Abra o build gerado e consulte `Console Output`.

O pipeline executa:

1. Verificacao dos servicos Docker.
2. Testes JUnit.
3. Teste funcional Selenium.
4. Teste de desempenho JMeter.

## Rodar testes separadamente

JUnit:

```powershell
docker exec irs-jenkins-qa sh -lc "cd /workspace && mvn clean test"
```

Selenium:

```powershell
docker exec irs-jenkins-qa sh -lc "cd /workspace/qa/selenium && mvn clean compile exec:java"
```

Para visualizar o navegador do Selenium, acesse:

```text
http://localhost:7900
senha: secret
```

JMeter:

```powershell
docker exec irs-jenkins-qa sh -lc "rm -rf /workspace/qa/results && mkdir -p /workspace/qa/results/html-report && jmeter -n -t /workspace/qa/jmeter/ibmec-research-stars.jmx -l /workspace/qa/results/ibmec-research-stars-results.jtl -e -o /workspace/qa/results/html-report"
```

## Resultados

O JMeter gera resultados em:

```text
qa/results/
```

Esses arquivos sao gerados em tempo de execucao e nao precisam ser versionados..

As metricas do JMeter sao gravadas no InfluxDB e visualizadas no Grafana em:

```text
Dashboards > JMeter > JMeter IBMEC Research Stars
```



