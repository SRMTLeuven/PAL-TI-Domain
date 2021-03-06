/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Matthias Hannes Koen Demonie David Op de Beeck
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package be.peerassistedlearning.model;

import be.peerassistedlearning.common.model.archivable.Archivable;
import be.peerassistedlearning.common.model.jpa.JPAEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class used to specify a Lesson
 *
 * @see JPAEntity
 */
@Entity
@Table( name = "lesson" )
public class Lesson extends JPAEntity<Integer> implements Archivable{
    @NotNull( message = "{NotNull.Lesson.date}" )
    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "date", nullable = false )
    private Date date;

    @NotEmpty( message = "{NotEmpty.Lesson.name}" )
    @Column( name = "name", nullable = false )
    private String name;

    @NotEmpty( message = "{NotEmpty.Lesson.description}" )
    @Column( name = "description", nullable = false, columnDefinition = "TEXT" )
    private String description;

    @Min( value = 1, message = "{Min.Lesson.duration}" )
    @Column( name = "duration" )
    private long duration;

    @Min( value = 1, message = "{Min.Lesson.maxParticipants}" )
    @Column( name = "max_participants", nullable = false )
    private int maxParticipants;

    @Valid
    @NotNull( message = "{NotNull.Lesson.course}" )
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "course_id" )
    private Course course;

    @Valid
    @NotNull( message = "{NotNull.Lesson.tutor}" )
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "tutor_id" )
    private Tutor tutor;

    @Valid
    @NotNull( message = "{NotNull.Lesson.room}" )
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "room_id" )
    private Room room;

    @Valid
    @NotNull( message = "{NotNull.Lesson.backupRoom}" )
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "backup_room_id" )
    private Room backupRoom;

    @Valid
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable(
            name = "lesson_booking",
            joinColumns = @JoinColumn( name = "lesson_id", referencedColumnName = "id" ),
            inverseJoinColumns = @JoinColumn( name = "student_id", referencedColumnName = "id" ) )
    private Set<Student> bookings;

    @Valid
    @OneToMany( mappedBy = "lesson", fetch = FetchType.EAGER, orphanRemoval = true )
    private Set<Review> reviews;

    @OneToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "request_id" )
    private Request request;

    /**
     * Default constructor for Lesson
     */
    public Lesson(){
    }

    /**
     * Constructor for Lesson
     *
     * @param date            The date of the lesson
     * @param name            The name of the lesson
     * @param description     The description of the lesson
     * @param duration        The duration of the lesson
     * @param course          The course of the lesson
     * @param maxParticipants The maximum number of participants of the course
     * @param tutor           The tutor of the course
     * @param room            The room for the lesson
     * @param backupRoom      The backup room for the lesson
     */
    public Lesson( Date date, String name, String description, long duration, Course course, int maxParticipants, Tutor tutor, Room room, Room backupRoom ){
        this.date = date;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.course = course;
        this.maxParticipants = maxParticipants;
        this.tutor = tutor;
        this.room = room;
        this.backupRoom = backupRoom;
    }

    /**
     * Constructor for Lesson
     *
     * @param date            The date of the lesson
     * @param name            The name of the lesson
     * @param description     The description of the lesson
     * @param duration        The duration of the lesson
     * @param course          The course of the lesson
     * @param maxParticipants The maximum number of participants of the course
     * @param tutor           The tutor of the course
     * @param room            The room for the lesson
     * @param backupRoom      The backup room for the lesson
     * @param request         The request bound to this lesson
     */
    public Lesson( Date date, String name, String description, long duration, Course course, int maxParticipants, Tutor tutor, Room room, Room backupRoom, Request request ){
        this( date, name, description, duration, course, maxParticipants, tutor, room, room );
        this.request = request;
    }

    /**
     * Adds a given booking to a lesson
     *
     * @param student The given student to add to the set of bookings for this lesson
     * @return true if this lesson's bookings set did not already contain the specified student
     */
    public boolean addBooking( Student student ){
        if( bookings == null )
            bookings = new HashSet<Student>();
        return bookings.add( student );
    }

    /**
     * Removes a given booking of a lesson
     *
     * @param student The given student to be removed from this lesson's set of bookings
     * @return true if this lesson's bookings set contained the specified student
     */
    public boolean removeBooking( Student student ){
        return bookings.remove( student );
    }

    /**
     * @return The request bound to this lesson
     */
    public Request getRequest(){
        return request;
    }

    /**
     * Sets the request bound to this lesson
     *
     * @param request The given request for this lesson
     */
    public void setRequest( Request request ){
        this.request = request;
    }

    /**
     * @return The date and time of the lesson
     */
    public Date getDate(){
        return date;
    }

    /**
     * Sets the date and time of the lesson
     *
     * @param date The date of the lesson
     */
    public void setDate( Date date ){
        this.date = date;
    }

    /**
     * @return The name of the lesson
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the lesson
     *
     * @param name The name of the lesson
     */
    public void setName( String name ){
        this.name = name;
    }

    /**
     * @return The description of the lesson
     */
    public String getDescription(){
        return description;
    }

    /**
     * Sets the description of the lesson
     *
     * @param description The description of the lesson
     */
    public void setDescription( String description ){
        this.description = description;
    }

    /**
     * @return The duration of the lesson
     */
    public long getDuration(){
        return duration;
    }

    /**
     * Sets the duration of the lesson
     *
     * @param duration The duration of the lesson
     */
    public void setDuration( long duration ){
        this.duration = duration;
    }

    /**
     * @return The course of the lesson
     */
    public Course getCourse(){
        return course;
    }

    /**
     * Sets the course of the lesson
     *
     * @param course The course of the lesson
     */
    public void setCourse( Course course ){
        this.course = course;
    }

    /**
     * @return The maximum number of participants of the lesson
     */
    public int getMaxParticipants(){
        return maxParticipants;
    }

    /**
     * Sets the maximum number of participants of the lesson
     *
     * @param maxParticipants The maximum number of participants of the lesson
     */
    public void setMaxParticipants( int maxParticipants ){
        this.maxParticipants = maxParticipants;
    }

    /**
     * @return The tutor and time of the lesson
     */
    public Tutor getTutor(){
        return tutor;
    }

    /**
     * Sets the tutor of the lesson
     *
     * @param tutor The tutor of the lesson
     */
    public void setTutor( Tutor tutor ){
        this.tutor = tutor;
    }

    /**
     * @return The set of bookings
     */
    public Set<Student> getBookings(){
        return bookings;
    }

    /**
     * Sets the bookings of the lesson
     *
     * @param bookings The Set of students enrolled for the lesson
     */
    public void setBookings( Set<Student> bookings ){
        this.bookings = bookings;
    }

    /**
     * @return The room of the lesson
     */
    public Room getRoom(){
        return room;
    }

    /**
     * Sets the room of the lesson
     *
     * @param room The room of the lesson
     */
    public void setRoom( Room room ){
        this.room = room;
    }

    /**
     * @return The backup room of the lesson
     */
    public Room getBackupRoom(){
        return backupRoom;
    }

    /**
     * Sets the backup room (when the room is occupied) of the lesson
     *
     * @param backupRoom The backup room of the lesson
     */
    public void setBackupRoom( Room backupRoom ){
        this.backupRoom = backupRoom;
    }

    /**
     * @return The date to be used by the timeline
     */
    public Date getArchiveDate(){
        return date;
    }

    public Set<Review> getReviews(){
        return reviews;
    }
}
