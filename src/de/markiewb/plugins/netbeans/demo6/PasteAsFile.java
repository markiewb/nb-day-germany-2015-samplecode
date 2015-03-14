/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.markiewb.plugins.netbeans.demo6;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.util.*;
import org.openide.util.NbBundle.Messages;
import org.openide.util.datatransfer.ExClipboard;

@ActionID(category = "Editing",id = "sample.PasteAsFile")
@ActionRegistration(displayName = "#CTL_PasteAsFile")
@ActionReferences({@ActionReference(path = "Menu/Edit", position = 0)})
@Messages("CTL_PasteAsFile=Paste content to new file")
public final class PasteAsFile implements ActionListener {
    private final DataFolder context;
    public PasteAsFile(DataFolder context) {this.context = context;}

    public String getClipboard() {
        Transferable t = Lookup.getDefault().lookup(ExClipboard.class).getContents(null);
        try {
            if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return (String) t.getTransferData(DataFlavor.stringFlavor);
            }
        } catch (UnsupportedFlavorException | IOException e) {}
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        try {
            if (null == getClipboard()) {return;}
            String fileName = JOptionPane.showInputDialog("File name", "foo.txt");
            FileObject file = FileUtil.createData(context.getPrimaryFile(), fileName);
            try (PrintWriter to = new PrintWriter(file.getOutputStream())) {
                to.print((String) getClipboard());
            }
            //open newly created file in editor
            DataObject.find(file).getLookup().lookup(OpenCookie.class).open();
        } catch (IOException ex) {Exceptions.printStackTrace(ex);}
    }
}
