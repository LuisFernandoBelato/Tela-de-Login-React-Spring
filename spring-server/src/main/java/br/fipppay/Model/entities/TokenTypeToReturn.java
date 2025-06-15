package br.fipppay.Model.entities;

public class TokenTypeToReturn
{
    public String token;
    public String level;

    public TokenTypeToReturn (String token, String level)
    {
        this.token = token;
        this.level = level;
    }
}
