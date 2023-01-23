import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Post")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPost;
    @Column(name = "created_at")
    private Date create_at;
    @Column(name = "updated_at")
    private Date updated_at;
    @ManyToOne
    @JoinColumn(name="idUsuarios")
    private Usuarios idUsuario;

    public  Post(){

    }

    public Post(Date create_at, Date updated_at) {
        this.create_at = create_at;
        this.updated_at = updated_at;
    }

    public int getIdPost() {
        return idPost;
    }


    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

}
