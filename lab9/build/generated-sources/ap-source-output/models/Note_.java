package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.User;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-04-09T08:59:53")
@StaticMetamodel(Note.class)
public class Note_ { 

    public static volatile SingularAttribute<Note, User> owner;
    public static volatile SingularAttribute<Note, String> contents;
    public static volatile SingularAttribute<Note, Integer> noteId;
    public static volatile SingularAttribute<Note, String> title;

}