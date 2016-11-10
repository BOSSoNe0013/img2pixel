import org.apache.commons.lang.StringUtils;
import utils.ArgumentsParser;

import java.awt.image.BufferedImage;
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
        if(args.length > 0 && args[0] != null){
            try {
                ArgumentsParser parser = new ArgumentsParser();
                parser.parse(args);
                BufferedImage img = parser.getBufferedImage();
                if (img != null) {
                    int width = img.getWidth();
                    int height = img.getHeight();
                    int pixelSize = parser.getRatio();
                    int factor = parser.getFactor();
                    List<String> pixels = new ArrayList<String>();
                    String firstPixel = "#000000";
                    for (int i = 0; i < width; i += pixelSize) {
                        for (int j = 0; j < height; j += pixelSize) {
                            int p = img.getRGB(i, j);
                            if (i == 0 && j == 0) {
                                firstPixel = getARGBString(p);
                            }
                            String pixel = String.format("%dpx %dpx %s",
                                    i*factor, j*factor, getARGBString(p));
                            pixels.add(pixel);
                        }
                    }
                    String css = String.format(Locale.getDefault(),
                            "<style>\n#pixel{\n\twidth:%dpx;\n\theight:%dpx;\n\t}\n#pixel:after{\n\tcontent:'';\n\tdisplay:block;\n\twidth:%dpx;\n\theight:%dpx;\n\tbackground:%s;\n\tbox-shadow:%s;\n}\n</style><div id=\"pixel\"></div>",
                            width*factor, height*factor, pixelSize*factor, pixelSize*factor, firstPixel, StringUtils.join(pixels, ",\n\t"));
                    System.out.println(css);
                } else {
                    System.err.println("Can't open file " + parser.getFilePath());
                }
            }
            catch (Exception e){
                System.err.println(e.toString());
            }
        }
        else{
            System.err.println("img2pixel needs at least one argument which is the source image path");
        }
    }

    private static String getARGBString(int pixel){
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        float a = alpha / 255;
        return String.format(Locale.US,"rgba(%d, %d ,%d, %.2f)", red, green, blue, a);
    }
}
