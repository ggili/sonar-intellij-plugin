package org.sonar.ide.intellij.analysis;

import com.intellij.openapi.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: g_gili
 * Date: 2/18/13
 * Time: 10:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class SonarCompactAnalysis extends SonarAnalysisAdaptor {

    private SonarAnalysis remoteAnalysis;

    private SonarAnalysis currentLocalAnalysis;

    public SonarCompactAnalysis(Project project) {
        super(new SonarRemoteAnalysis(project));

        this.remoteAnalysis = this.sonarAnalysis;
    }

    public void switchToLocalAnalysis(SonarAnalysis localAnalysis) {
        this.sonarAnalysis = localAnalysis;
        this.currentLocalAnalysis = localAnalysis;
    }

    public void switchToRemoteAnalysis() {
        if (this.remoteAnalysis == null) {
            throw new IllegalStateException("Remote analysis is not available");
        }
        this.sonarAnalysis = this.remoteAnalysis;
    }

    public void switchToCurrentLocalAnalysis() {
        if (this.currentLocalAnalysis == null) {
            throw new IllegalStateException("Local analysis is not available");
        }
        this.sonarAnalysis = this.currentLocalAnalysis;
    }

    public boolean isLocalAnalysisAvailable() {
        return this.currentLocalAnalysis != null;
    }
}
