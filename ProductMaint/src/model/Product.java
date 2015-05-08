package model;

import java.text.NumberFormat;

public class Product
{
    private String code;
    private String description;
    private double price;

    public Product()
    {
        this.code = "";
        this.description = "";
        this.price = 0;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public double getPrice()
    {
        return price;
    }

    public String getFormattedPrice()
    {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof Product)
        {
            Product product2 = (Product) object;
            if
            (
                code.equals(product2.getCode()) &&
                description.equals(product2.getDescription()) &&
                price == product2.getPrice()
            )
                return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        return "Code:        " + code + "\n" +
               "Description: " + description + "\n" +
               "Price:       " + this.getFormattedPrice() + "\n";
    }
    
    public boolean correctFormat(String code) {
        // make sure code length is exactly 4 characters long
        if (code.length() != 4)
            return false;
        
        // if it is 4 characters long then iterate through string
        // return false if character is not a letter or digit
        for (int i=0 ; i<code.length() ; i++) {
            char c = code.charAt(i);
            if (!Character.isLetterOrDigit(c))
                return false;
        }
        
        // passed all the tests so return true
        return true;
    }
}