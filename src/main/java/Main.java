import org.bytedeco.javacpp.opencv_core;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.bytedeco.javacpp.opencv_imgcodecs.*;

/**
 * Copyright (C) 2015 Cyril Bosselut <bossone0013@gmail.com>
 * <p>
 * This file is part of NeoJava examples for UDOO
 * <p>
 * NeoJava examples for UDOO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This libraries are distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class Main {
    public static void main(String[] args){
        if(args.length > 0 && args[0] != null){
            opencv_core.IplImage img = cvLoadImage(args[0]);
            if (img != null) {
                int width = img.width();
                int height = img.height();
                int pixelSize = 4;
                if (args.length > 1) {
                    int ps = Integer.parseInt(args[1]);
                    if(ps > 0) {
                        pixelSize = ps;
                    }
                }
                List<String> pixels = new ArrayList<String>();
                String firstPixel = "#000000";
                for (int i = 0; i < width; i += pixelSize) {
                    for (int j = 0; j < height; j += pixelSize) {
                        opencv_core.CvScalar scalar = opencv_core.cvGet2D(img, j, i);
                        if(i == 0 && j == 0){
                            firstPixel = String.format(Locale.getDefault(), "#%02X%02X%02X",
                                    (int) scalar.val(2), (int) scalar.val(1), (int) scalar.val(0));
                        }
                        String pixel = String.format("%dpx %dpx #%02X%02X%02X",
                                i, j, (int) scalar.val(2), (int) scalar.val(1), (int) scalar.val(0));
                        pixels.add(pixel);
                    }
                }
                img.release();
                @SuppressWarnings("Since15") String css = String.format(Locale.getDefault(),
                        "<style>\n#pixel{\n\twidth:%dpx;\n\theight:%dpx;\n\t}\n#pixel:after{\n\tcontent:'';\n\tdisplay:block;\n\twidth:%dpx;\n\theight:%dpx;\n\tbackground:%s;\n\tbox-shadow:%s;\n}\n</style><div id=\"pixel\"></div>",
                        width, height, pixelSize, pixelSize, firstPixel, String.join(",\n\t", pixels));
                System.out.println(css);
            }
            else{
                System.err.println("Can't open file " + args[0]);
            }
        }
        else{
            System.err.println("img2pixel needs at least one argument which is the source image path");
        }
    }
}
