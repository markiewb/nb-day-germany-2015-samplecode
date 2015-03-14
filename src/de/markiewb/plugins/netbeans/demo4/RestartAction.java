package de.markiewb.plugins.netbeans.demo4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.LifecycleManager;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "File", id = "sample.RestartAction")
@ActionRegistration(displayName = "#CTL_RestartAction")
@ActionReference(path = "Menu/File", position = 2700, separatorBefore = 2650)
@Messages("CTL_RestartAction=Restart")
public final class RestartAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        LifecycleManager.getDefault().markForRestart();
        LifecycleManager.getDefault().exit();
    }
}
