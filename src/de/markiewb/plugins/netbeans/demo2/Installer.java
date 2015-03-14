package de.markiewb.plugins.netbeans.demo2;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.openide.awt.StatusDisplayer;
import org.openide.loaders.DataObject;
import org.openide.modules.ModuleInstall;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

public class Installer extends ModuleInstall {

    final PropertyChangeListener propListener = new PropertyChangeListener() {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            String title = null;
            TopComponent activeTC = TopComponent.getRegistry().getActivated();
            if (null != activeTC) {
                DataObject dataObject = activeTC.getLookup().lookup(DataObject.class);
                if (null != dataObject) {
                    title = dataObject.getPrimaryFile().getPath();
                }
                WindowManager.getDefault().getMainWindow().setTitle(title);
                StatusDisplayer.getDefault().setStatusText("Current file: " + title);
            }
        }
    };

    @Override
    public void restored() {
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run() {
                TopComponent.getRegistry().addPropertyChangeListener(propListener);
            }
        });
    }

    @Override
    public void uninstalled() {
        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {
            @Override
            public void run() {
                TopComponent.getRegistry().removePropertyChangeListener(propListener);
            }
        });
    }

}
