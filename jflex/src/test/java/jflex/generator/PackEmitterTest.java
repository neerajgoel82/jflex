/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * JFlex 1.8.0-SNAPSHOT                                                    *
 * Copyright (C) 1998-2018  Gerwin Klein <lsf@jflex.de>                    *
 * All rights reserved.                                                    *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package jflex.generator;

import junit.framework.TestCase;

/**
 * PackEmitterTest
 *
 * @author Gerwin Klein
 * @version JFlex 1.8.0-SNAPSHOT
 */
public class PackEmitterTest extends TestCase {

  private static final String NL = "\n";
  private PackEmitter p;

  /** Constructor for PackEmitterTest. */
  public PackEmitterTest() {
    super("PackEmitter test");
  }

  public void setUp() {
    p =
        new PackEmitter("Bla") {
          public void emitUnpack() {}
        };
  }

  public void testInit() {
    p.emitInit();
    assertEquals(
        "  private static final int [] ZZ_BLA = zzUnpackBla();"
            + NL
            + NL
            + "  private static final String ZZ_BLA_PACKED_0 ="
            + NL
            + "    \"",
        p.toString());
  }

  public void testEmitUCplain() {
    p.emitUC(8);
    p.emitUC(0xFF00);

    assertEquals("\\10\\uff00", p.toString());
  }

  public void testLineBreak() {
    for (int i = 0; i < 36; i++) {
      p.breaks();
      p.emitUC(i);
    }
    System.out.println(p);
    assertEquals(
        "\\0\\1\\2\\3\\4\\5\\6\\7\\10\\11\\12\\13\\14\\15\\16\\17\"+"
            + NL
            + "    \"\\20\\21\\22\\23\\24\\25\\26\\27\\30\\31\\32\\33\\34\\35\\36\\37\"+"
            + NL
            + "    \"\\40\\41\\42\\43",
        p.toString());
  }
}
