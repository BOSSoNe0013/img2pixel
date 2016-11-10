package com.b1project.img2pixel.utils;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Copyright (C) 2016 Cyril Bosselut <bossone0013@gmail.com>
 * <p>
 * This file is part of img2pixel
 * <p>
 * img2pixel is free software: you can redistribute it and/or modify
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
public class ArgumentsParser {

    private BufferedImage mBufferedImage;
    private CmdLineParser mParser;
    private File mOutputFile = null;
    private int mRatio = 4;
    private float mFactor = 1;
    private boolean mHelpNeeded = false;

    public ArgumentsParser(){
        mParser = new CmdLineParser(this);
    }

    @Option(name = "-p", aliases = {"--pseudo-pixel"}, usage = "set the pseudo pixel size mRatio (1 or more), default 4")
    public void setRatio(int ratio){
        if (ratio > 0){
            mRatio = ratio;
        }
    }

    @Option(name = "-m", aliases = {"--multiply"}, usage = "increase the output pixel size, default 1")
    public void setFactor(float factor){
        if (factor > 0){
            mFactor = factor;
        }
    }

    @Option(name = "-f", aliases = {"--file"}, required = true, usage ="image access path")
    public void setFile(File file){
        try {
            FileInputStream is = new FileInputStream(file);
            if (is.available() > 0){
                mBufferedImage = ImageIO.read(is);
            }
            is.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            mParser.printUsage(System.err);
        }
    }

    @Option(name = "-o", aliases = {"--output"}, usage ="output target path, default output to stdout")
    public void setOutput(File file){
        mOutputFile = file;
    }

    @Option(name = "-h", aliases = {"--help"}, usage = "this help", help = true)
    public void showHelp(String help){
        mParser.printUsage(System.err);
        mHelpNeeded = true;
    }

    public boolean parse(String... args){
        boolean success = false;
        try {
            mParser.parseArgument(args);
            if (getRatio() < 1){
                System.err.println("Ratio should be >= 1");
                mParser.printUsage(System.err);
                return false;
            }
            if (getFactor() <= 0){
                System.err.println("Factor should be > 0");
                mParser.printUsage(System.err);
                return false;
            }
            if(mBufferedImage != null) {
                success = true;
            }
        }
        catch (CmdLineException e){
            System.err.println(e.getMessage());
            mParser.printUsage(System.err);
        }
        return success;
    }

    public int getRatio(){
        return mRatio;
    }

    public float getFactor(){
        return mFactor;
    }

    public BufferedImage getBufferedImage(){
        return mBufferedImage;
    }

    public boolean isHelpNeeded(){
        return mHelpNeeded;
    }

    public File getOutputFile(){
        return mOutputFile;
    }
}
