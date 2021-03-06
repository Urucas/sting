package com.urucas.sting.model;

/**
 * Created by Urucas on 7/23/14.
 */
public class SlideNamespace {


    private int id;
    private String name, namespace, desc, type;

    public SlideNamespace(){

    }

    public SlideNamespace(int id, String name, String namespace, String desc, String type) {
        this.setId(id);
        this.setName(name);
        this.setNamespace(namespace);
        this.setDesc(desc);
        this.setType(type);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

