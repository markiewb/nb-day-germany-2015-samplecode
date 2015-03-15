/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.markiewb.plugins.netbeans.demo8;

import org.junit.Test;
import org.netbeans.modules.java.hints.test.api.HintTest;
import org.openide.filesystems.FileUtil;

/* TODO to make this test work:
 - to ensure that the newest Java language features supported by the IDE are available,
 regardless of which JDK you build the module with:
 -- for Ant-based modules, add "requires.nb.javac=true" into nbproject/project.properties
 -- for Maven-based modules, use dependency:copy in validate phase to create
 target/endorsed/org-netbeans-libs-javacapi-*.jar and add to endorseddirs
 in maven-compiler-plugin and maven-surefire-plugin configuration
 See: http://wiki.netbeans.org/JavaHintsTestMaven
 */
public class ConvertToAssertTrueFalseHintTest {


    @Test
    public void testFixWorking() throws Exception {
        HintTest.create()
                .input("package test;\n"
                        + "public class Test {\n"
                        + "    public static void main(String[] args) {\n"
                        + "        junit.framework.Assert.assertEquals(true, false);\n"
                        + "    }\n"
                        + "}\n")
                .classpath(FileUtil.getArchiveRoot(junit.framework.Assert.class.getProtectionDomain().getCodeSource().getLocation()))
                .run(ConvertToAssertTrueFalseHint.class)
                .findWarning("3:31-3:43:verifier:" + Bundle.ERR_ConvertToAssertTrueFalseHint())
                .applyFix()
                .assertCompilable()
                .assertOutput("package test;\n"
                        + "import junit.framework.Assert;\n"
                        + "public class Test {\n"
                        + "    public static void main(String[] args) {\n"
                        + "        Assert.assertTrue(false);\n"
                        + "    }\n"
                        + "}\n");
    }
}
