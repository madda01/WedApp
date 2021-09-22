package com.example.dreamwedmadd.models;

public class Photographermodel {

    private int id;
    private String fnamee,lnamee,emaile,addresse,comanpnynamee,descriptione,phonee ;
    private double pricee;
    private byte[] image;


    public Photographermodel(String fnamee, String lnamee, String emaile,String phonee,String comanpnynamee,String addresse ,double pricee, String descriptione, byte[] image) {
        this.fnamee = fnamee;
        this.lnamee = lnamee;
        this.emaile = emaile;
        this.addresse = addresse;
        this.comanpnynamee = comanpnynamee;
        this.descriptione = descriptione;
        this.phonee = phonee;
        this.pricee = pricee;
        this.image = image;
    }

    public Photographermodel(int id, String fnamee, String lnamee, String emaile,String phonee,String comanpnynamee,String addresse ,double pricee, String descriptione, byte[] image) {
        this.id = id;
        this.fnamee = fnamee;
        this.lnamee = lnamee;
        this.emaile = emaile;
        this.addresse = addresse;
        this.comanpnynamee = comanpnynamee;
        this.descriptione = descriptione;
        this.phonee = phonee;
        this.pricee = pricee;
        this.image = image;
    }

    public Photographermodel(){}

    public Photographermodel(int id, String fnamee, String lnamee, String emaile,String phonee,String comanpnynamee,String addresse ,double pricee, String descriptione) {
        this.id = id;
        this.fnamee = fnamee;
        this.lnamee = lnamee;
        this.emaile = emaile;
        this.addresse = addresse;
        this.comanpnynamee = comanpnynamee;
        this.pricee = pricee;
        this.descriptione = descriptione;
        this.phonee = phonee;
    }

    public Photographermodel(String fnamee, String lnamee, String emaile,String phonee,String comanpnynamee,String addresse ,double pricee, String descriptione) {
        this.fnamee = fnamee;
        this.lnamee = lnamee;
        this.emaile = emaile;
        this.addresse = addresse;
        this.comanpnynamee = comanpnynamee;
        this.pricee = pricee;
        this.descriptione = descriptione;
        this.phonee = phonee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFnamee() {
        return fnamee;
    }

    public void setFnamee(String fnamee) {
        this.fnamee = fnamee;
    }

    public String getLnamee() {
        return lnamee;
    }

    public void setLnamee(String lnamee) {
        this.lnamee = lnamee;
    }

    public String getEmaile() {
        return emaile;
    }

    public void setEmaile(String emaile) {
        this.emaile = emaile;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getComanpnynamee() {
        return comanpnynamee;
    }

    public void setComanpnynamee(String comanpnynamee) {
        this.comanpnynamee = comanpnynamee;
    }

    public double getPricee() {
        return pricee;
    }

    public void setPricee(double pricee) {
        this.pricee = pricee;
    }

    public String getDescriptione() {
        return descriptione;
    }

    public void setDescriptione(String descriptione) {
        this.descriptione = descriptione;
    }

    public String getPhonee() {
        return phonee;
    }

    public void setPhonee(String phonee) {
        this.phonee = phonee;
    }
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
