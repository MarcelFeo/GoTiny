package GoTiny.Links;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "links_user")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String urlLong;
    private String urlShort;
    private String urlQrCode;
    private LocalDateTime urlCreated_at;

    public Link(UUID id, String urlLong, String urlShort, String urlQrCode, LocalDateTime urlCreated_at) {
        this.id = id;
        this.urlLong = urlLong;
        this.urlShort = urlShort;
        this.urlQrCode = urlQrCode;
        this.urlCreated_at = urlCreated_at;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrlLong() {
        return urlLong;
    }

    public void setUrlLong(String urlLong) {
        this.urlLong = urlLong;
    }

    public String getUrlShort() {
        return urlShort;
    }

    public void setUrlShort(String urlShort) {
        this.urlShort = urlShort;
    }

    public String getUrlQrCode() {
        return urlQrCode;
    }

    public void setUrlQrCode(String urlQrCode) {
        this.urlQrCode = urlQrCode;
    }

    public LocalDateTime getUrlCreated_at() {
        return urlCreated_at;
    }

    public void setUrlCreated_at(LocalDateTime urlCreated_at) {
        this.urlCreated_at = urlCreated_at;
    }
}
