package io.qualys.iac.jenkins;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.qualys.iac.commons.model.ScanResult;
import hudson.model.TaskListener;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import hudson.model.Run;
import java.io.IOException;
import java.net.URISyntaxException;
import jenkins.model.RunAction2;
import lombok.Setter;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

public class ScanResultAction implements RunAction2 {

    private transient Run run;

    @DataBoundSetter
    @Setter
    public ScanResult scanResult;

    public String getScanResult() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(scanResult);
    }
    @DataBoundConstructor
    @SuppressFBWarnings({"UC_USELESS_OBJECT"})
    public ScanResultAction(TaskListener listener, ScanResult scanResult) throws IOException, URISyntaxException {
        this.scanResult = scanResult;
        if (listener != null) {
            listener.getLogger().println("Qualys IaC Scan : Executing Scan Result Action for Jenkins Job");
        }
    }

    @Override
    public String getIconFileName() {
        return "/plugin/qualys-iac-scan/icons/qualyscloud-48x48.png";
    }

    @Override
    public String getDisplayName() {
        return "Qualys IaC Scan Report";
    }

    @Override
    public String getUrlName() {
        return "iac_scan_result";
    }

    @Override
    public void onAttached(Run<?, ?> run) {
        this.run = run;
    }

    @Override
    public void onLoad(Run<?, ?> run) {
        this.run = run;
    }

    public Run getRun() {
        return run;
    }
}
