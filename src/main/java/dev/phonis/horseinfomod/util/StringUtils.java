package dev.phonis.horseinfomod.util;

import dev.phonis.horseinfomod.config.HIMConfig;

import java.math.BigDecimal;
import java.math.MathContext;

public
class StringUtils
{

    public static
    String formatDouble(double number)
    {
        if (HIMConfig.INSTANCE.roundNumbers)
        {
            return new BigDecimal(number).round(new MathContext(HIMConfig.INSTANCE.roundingPrecision))
                .stripTrailingZeros().toPlainString();
        }
        return Double.toString(number);
    }

}
