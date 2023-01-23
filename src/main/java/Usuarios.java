import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Usuarios")
public class Usuarios implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuarios;
    @Column(name = "Nombre")
    private String Nombre;
    @Column(name = "Apellidos")
    private String Apellidos;
    @Column(name = "Username")
    private String Username;
    @Column(name = "Password")
    private String Password;
    @Column(name = "Email")
    private String Email;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="idUsuarios")
    private List<Post> listaPost;
    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="idUsuarios")
    private List<Likes> listaLikes;


    public Usuarios (){

    }

    public Usuarios(String nombre, String apellidos, String username, String password, String email) {
        Nombre = nombre;
        Apellidos = apellidos;
        Username = username;
        Password = password;
        Email = email;
    }

    public int getIdUsuarios() {
        return idUsuarios;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public List<Post> getListaPost() {
        return listaPost;
    }

    public void setListaPost(List<Post> listaPost) {
        this.listaPost = listaPost;
    }

    public List<Likes> getListaLikes() {
        return listaLikes;
    }

    public void setListaLikes(List<Likes> listaLikes) {
        this.listaLikes = listaLikes;
    }
}

