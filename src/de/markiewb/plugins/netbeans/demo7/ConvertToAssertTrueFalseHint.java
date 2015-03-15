/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.markiewb.plugins.netbeans.demo7;

import org.netbeans.spi.editor.hints.ErrorDescription;
import org.netbeans.spi.editor.hints.Fix;
import org.netbeans.spi.java.hints.ErrorDescriptionFactory;
import org.netbeans.spi.java.hints.Hint;
import org.netbeans.spi.java.hints.HintContext;
import static org.netbeans.spi.java.hints.JavaFixUtilities.rewriteFix;
import org.netbeans.spi.java.hints.TriggerPattern;
import org.netbeans.spi.java.hints.TriggerPatterns;
import org.openide.util.NbBundle.Messages;

@Hint(displayName = "#DN_ConvertToAssertTrueFalseHint", description = "#DESC_ConvertToAssertTrueFalseHint", category = "general")
@Messages({
    "DN_ConvertToAssertTrueFalseHint=Convert to AssertTrue/False",
    "DESC_ConvertToAssertTrueFalseHint=Convert to AssertTrue/False"
})
public class ConvertToAssertTrueFalseHint {

    @TriggerPatterns({
        @TriggerPattern(value = "junit.framework.Assert.assertEquals(true, $anyCaseA)"),
        @TriggerPattern(value = "junit.framework.Assert.assertEquals(false, $anyCaseB)")
    }
    )
    @Messages("ERR_ConvertToAssertTrueFalseHint=Convert to AssertTrue/False")
    public static ErrorDescription computeWarning(HintContext ctx) {
        //or more complex logic based on the TreePaths
        if (ctx.getVariables().containsKey("$anyCaseA")) {
            Fix fix = rewriteFix(ctx, Bundle.ERR_ConvertToAssertTrueFalseHint(), ctx.getPath(), "junit.framework.Assert.assertTrue($anyCaseA)");
            return ErrorDescriptionFactory.forName(ctx, ctx.getPath(), Bundle.ERR_ConvertToAssertTrueFalseHint(), fix);
        }
        if (ctx.getVariables().containsKey("$anyCaseB")) {
            Fix fix = rewriteFix(ctx, Bundle.ERR_ConvertToAssertTrueFalseHint(), ctx.getPath(), "junit.framework.Assert.assertFalse($anyCaseB)");
            return ErrorDescriptionFactory.forName(ctx, ctx.getPath(), Bundle.ERR_ConvertToAssertTrueFalseHint(), fix);
        }
        return null;
    }
}
