package cours.uahb.model;

public class Response
{
    private String status;

    public Response(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
