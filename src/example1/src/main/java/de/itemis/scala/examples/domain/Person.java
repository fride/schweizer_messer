package de.itemis.scala.examples.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 07.04.12
 * Time: 22:35
 *
 * @author Friderici
 */
@Entity
public class Person {

    @Id
    private String login;

    @Column(nullable = false)
    private String email;

    private int age;

    @OneToMany
    private List<Post> posts = new ArrayList<Post>();

    @OneToMany
    private List<Comment> userComments = new ArrayList<Comment>();

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Comment> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<Comment> userComments) {
        this.userComments = userComments;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
