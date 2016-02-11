package be.peerassistedlearningti.service;

import be.peerassistedlearningti.common.dao.DAOException;
import be.peerassistedlearningti.common.service.ServiceException;
import be.peerassistedlearningti.dao.*;
import be.peerassistedlearningti.model.*;

import java.util.Collection;

/**
 * Class used to access the backend functionalities
 *
 * @see PALService
 */
public class PALServiceImpl implements PALService
{

    /**
     * Data access objects
     */

    private TutorDAO tutorDAO;
    private LessonDAO lessonDAO;
    private CourseDAO courseDAO;
    private StudentDAO studentDAO;
    private RoomDAO roomDAO;
    private BookingDAO bookingDAO;

    /**
     * Sets the course dao for the service
     *
     * @param courseDAO The course dao for the service
     */
    public void setCourseDAO( CourseDAO courseDAO )
    {
        this.courseDAO = courseDAO;
    }

    /**
     * Sets the student dao for the service
     *
     * @param studentDAO The student dao for the service
     */
    public void setStudentDAO( StudentDAO studentDAO )
    {
        this.studentDAO = studentDAO;
    }

    /**
     * Sets the tutor dao for the service
     *
     * @param tutorDAO The tutor dao for the service
     */
    public void setTutorDAO( TutorDAO tutorDAO )
    {
        this.tutorDAO = tutorDAO;
    }

    /**
     * Sets the lesson dao for the service
     *
     * @param lessonDAO The lesson dao for the service
     */
    public void setLessonDAO( LessonDAO lessonDAO )
    {
        this.lessonDAO = lessonDAO;
    }

    /**
     * Sets the room dao for the service
     *
     * @param roomDAO The room dao for the service
     */
    public void setRoomDAO( RoomDAO roomDAO )
    {
        this.roomDAO = roomDAO;
    }

    /**
     * Sets the booking dao for the service
     *
     * @param bookingDAO The booking dao for the service
     */
    public void setBookingDAO( BookingDAO bookingDAO )
    {
        this.bookingDAO = bookingDAO;
    }

    /**
     * Adds a course to the database
     *
     * @param course The course to be added to the database
     */
    public void addCourse( Course course )
    {
        try
        {
            courseDAO.add( course );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Removes the specified course from the database
     *
     * @param course The course to be removed from the database
     */
    public void removeCourse( Course course )
    {
        try
        {
            courseDAO.remove( course );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Gets the course with the specified id
     *
     * @param id The id of the course
     * @return The course with the specified id
     */
    public Course getCourseById( int id )
    {
        try
        {
            return courseDAO.getById( id );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    public Collection<Course> getAllCourses()
    {
        try
        {
            return courseDAO.getAll();
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Adds a student to the database
     *
     * @param student The student to be added to the database
     */
    public void addStudent( Student student )
    {
        try
        {
            studentDAO.add( student );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Removes the specified student from the database
     *
     * @param student The student to be removed from the database
     */
    public void removeStudent( Student student )
    {
        try
        {
            studentDAO.add( student );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Gets the student with the specified id
     *
     * @param id The id of the student
     * @return The student with the specified id
     */
    public Student getStudentById( int id )
    {
        try
        {
            return studentDAO.getById( id );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Gets the student with the specified email
     *
     * @param email The email of the student
     * @return The student with the specified email
     */
    public Student getStudentByEmail( String email )
    {
        try
        {
            return studentDAO.getByEmail( email );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Adds a tutor to the database
     *
     * @param tutor The tutor to be added to the database
     */
    public void addTutor( Tutor tutor )
    {
        try
        {
            tutorDAO.add( tutor );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Removes the specified tutor from the database
     *
     * @param tutor The tutor to be removed from the database
     */
    public void removeTutor( Tutor tutor )
    {
        try
        {
            tutorDAO.remove( tutor );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Gets the tutor with the specified id
     *
     * @param id The id of the tutor
     * @return The tutor with the specified id
     */
    public Tutor getTutorById( int id )
    {
        try
        {
            return tutorDAO.getById( id );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Adds a lesson to the database
     *
     * @param lesson The lesson to be added to the database
     */
    public void addLesson( Lesson lesson )
    {
        try
        {
            lessonDAO.add( lesson );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Removes the specified lesson from the database
     *
     * @param lesson The lesson to be removed from the database
     */
    public void removeLesson( Lesson lesson )
    {
        try
        {
            lessonDAO.remove( lesson );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Gets the lesson with the specified id
     *
     * @param id The id of the lesson
     * @return The lesson with the specified id
     */
    public Lesson getLessonById( int id )
    {
        try
        {
            return lessonDAO.getById( id );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Adds a room to the database
     *
     * @param room The room to be added to the database
     */
    public void addRoom( Room room )
    {
        try
        {
            roomDAO.add( room );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Removes the specified room from the database
     *
     * @param room The room to be removed from the database
     */
    public void removeRoom( Room room )
    {
        try
        {
            roomDAO.remove( room );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Gets the room with the specified id
     *
     * @param id The id of the room
     * @return The room with the specified id
     */
    public Room getRoomById( int id )
    {
        try
        {
            return roomDAO.getById( id );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Gets the rooms with the specified campus
     *
     * @param campus The campus of the room
     * @return The rooms with the specified campus
     */
    public Collection<Room> getRoomsFromCampus( Campus campus )
    {
        try
        {
            return roomDAO.getFromCampus( campus );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }

    /**
     * Gets the booking with the specified id
     *
     * @param id The id of the booking
     * @return The booking with the specified id
     */
    public Booking getBookingById( int id )
    {
        try
        {
            return bookingDAO.getById( id );
        } catch ( DAOException e )
        {
            throw new ServiceException( e );
        }
    }
}
