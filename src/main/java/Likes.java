import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Likes")
public class Likes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLikes;
    @ManyToOne
    @JoinColumn(name="idUsuarios")
    private Usuarios idUsuario;
    @ManyToOne
    @JoinColumn(name="idPost")
    private Post idPost;

    public Likes() {
    }

    public int getIdLikes() {
        return idLikes;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Post getIdPost() {
        return idPost;
    }

    public void setIdPost(Post idPost) {
        this.idPost = idPost;
    }
}
