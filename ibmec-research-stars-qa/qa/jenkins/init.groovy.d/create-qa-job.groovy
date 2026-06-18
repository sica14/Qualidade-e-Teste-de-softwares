import hudson.security.AuthorizationStrategy
import hudson.security.SecurityRealm
import jenkins.model.Jenkins
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition
import org.jenkinsci.plugins.workflow.job.WorkflowJob

def jenkins = Jenkins.get()

jenkins.setSecurityRealm(SecurityRealm.NO_AUTHENTICATION)
jenkins.setAuthorizationStrategy(AuthorizationStrategy.UNSECURED)

def jobName = 'ibmec-research-stars-qa'
def scriptFile = new File('/workspace/Jenkinsfile.qa')

if (scriptFile.exists()) {
    def job = jenkins.getItem(jobName)

    if (job == null) {
        job = jenkins.createProject(WorkflowJob, jobName)
    }

    job.setDefinition(new CpsFlowDefinition(scriptFile.text, true))
    job.setDescription('Pipeline de QA: JUnit, Selenium WebDriver, JMeter, InfluxDB e Grafana.')
    job.save()
}

jenkins.save()
