package com.system.domain;

import java.io.Serializable;

public class EthData implements Serializable {
    //以太坊端的文件hex串
    private String file;
    //文件类型的hex串
    private String type;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EthFile{" +
                "file='" + file + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
