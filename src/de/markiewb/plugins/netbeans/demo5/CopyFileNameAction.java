package de.markiewb.plugins.netbeans.demo5;

import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.util.datatransfer.ExClipboard;

@ActionID(category = "Editing",id = "sample.CopyFileNameAction")
@ActionRegistration(
    displayName = "#CTL_CopyFileNameAction", iconBase = "de/markiewb/plugins/netbeans/demo5/netbeans.gif")
@ActionReferences({
    @ActionReference(path = "Toolbars/File", position = 0),
    @ActionReference(path = "UI/ToolActions/File", position = -100),
    @ActionReference(path = "Shortcuts", name = "DO-NUMPAD5")
})
@Messages("CTL_CopyFileNameAction=Copy filenames")
public final class CopyFileNameAction implements ActionListener {

    private final List<FileObject> ctx;
    public CopyFileNameAction(List<FileObject> ctx) {this.ctx = ctx;}

    @Override
    public void actionPerformed(ActionEvent ev) {
        List<String> list = new ArrayList<>();
        for (FileObject fileObject : ctx) {
            list.add(fileObject.getPath());
        }
        //copy to clipboard
        Lookup.getDefault().lookup(ExClipboard.class)
              .setContents(new StringSelection(list.toString()), null);
        JOptionPane.showMessageDialog(null, list);
    }
}