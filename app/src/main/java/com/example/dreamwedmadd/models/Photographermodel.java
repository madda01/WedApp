package com.example.dreamwedmadd.models;

public class Photographermodel {

    private int id;
    private String fnamee,lnamee,emaile,addresse,comanpnynamee,descriptione,phonee ;
    private double pricee;

    public Photographermodel(){}

    public Photographermodel(int id, String fnamee, String lnamee, String emaile, String addresse, String comanpnynamee, double pricee, String descriptione, String phonee) {
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

    public Photographermodel(String fnamee, String lnamee, String emaile, String addresse, String comanpnynamee, double pricee, String descriptione, String phonee) {
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
}
