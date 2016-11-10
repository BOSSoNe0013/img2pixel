package com.b1project.img2pixel;

import com.b1project.img2pixel.utils.ArgumentsParser;
import org.apache.commons.lang.StringUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Copyright (C) 2016 Cyril Bosselut <bossone0013@gmail.com>
 * <p>
 * This file is part of img2pixel
 * <p>
 * img2pixel is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 * <p>
 * This application is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class Main {

    public static void main(String[] args){
        try {
            ArgumentsParser parser = new ArgumentsParser();
            parser.parse(args);
            BufferedImage img = parser.getBufferedImage();
            if (img != null && !parser.isHelpNeeded()) {
                int width = img.getWidth();
                int height = img.getHeight();
                int pixelSize = parser.getRatio();
                float factor = parser.getFactor();
                List<String> pixels = new ArrayList<>();
                String firstPixel = "rgba(0,0,0,0)";
                for (int j = 0; j < height; j += pixelSize) {
                    for (int i = 0; i < width; i += pixelSize) {
                        int p = img.getRGB(i, j);
                        if (i == 0 && j == 0) {
                            firstPixel = getRGBAString(p);
                        }
                        String pixel = String.format("%dpx %dpx %s",
                                (int)(i*factor), (int)(j*factor), getRGBAString(p));
                        pixels.add(pixel);
                    }
                }
                String css = String.format(Locale.getDefault(),
                        "<style>\n#pixel{\n\twidth:%dpx;\n\theight:%dpx;\n\t}\n#pixel:after{\n\tcontent:'';\n\tdisplay:block;\n\twidth:%dpx;\n\theight:%dpx;\n\tbackground:%s;\n\tbox-shadow:%s;\n}\n</style><div id=\"pixel\"></div>",
                        (int)(width*factor), (int)(height*factor), (int)(pixelSize*factor), (int)(pixelSize*factor), firstPixel, StringUtils.join(pixels, ",\n\t"));
                File out = parser.getOutputFile();
                if (out != null){
                    FileOutputStream os = new FileOutputStream(out);
                    os.write(css.getBytes());
                    os.close();
                    System.out.println("All done.");
                }
                else {
                    System.out.println(css);
                }
            }
        }
        catch (Exception e){
            System.err.println(e.toString());
        }
    }

    private static String getRGBAString(int pixel){
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        float a = alpha / 255;
        return String.format(Locale.US,"rgba(%d, %d ,%d, %.2f)", red, green, blue, a);
    }
}
