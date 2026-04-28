package org.shriniwas.exceptions;

public class HeadlessNotSupportedException extends RuntimeException{

    public HeadlessNotSupportedException(String browser){
        super(String.format("Headless not supported for %s browser", browser));
    }

}
