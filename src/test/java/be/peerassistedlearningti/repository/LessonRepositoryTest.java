package be.peerassistedlearningti.repository;

import be.peerassistedlearningti.config.ApplicationConfig;
import be.peerassistedlearningti.model.*;
import be.peerassistedlearningti.util.Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = ApplicationConfig.class )
public class LessonRepositoryTest implements RepositoryTest
{

    private Lesson l1, l2;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Before
    public void before()
    {
        Course c1 = new Course( "MBI80x", ".NET Programmeren", ".NET", "TI", 3 );
        c1 = courseRepository.save( c1 );

        assertNotNull( c1 );

        Set<Course> courses = new HashSet<Course>();
        courses.add( c1 );

        Student s1 = new Student( "Koen", "paswoord", "koen1992@hotmail.com", UserType.ADMIN );
        s1 = studentRepository.save( s1 );

        assertNotNull( s1.getId() );

        Tutor t1 = new Tutor( s1, courses );
        t1 = tutorRepository.save( t1 );

        assertNotNull( t1.getId() );

        Room r1 = new Room( "2.25", Campus.PROXIMUS, RoomType.COMPUTER );
        Room r2 = new Room( "2.26", Campus.PROXIMUS, RoomType.COMPUTER );

        r1 = roomRepository.save( r1 );
        r2 = roomRepository.save( r2 );

        assertNotNull( r1.getId() );
        assertNotNull( r2.getId() );

        l1 = new Lesson( new Date(), "Test lesson", "Test description", 120L, c1, 25, t1, r1, r2 );
        l2 = new Lesson( new Date(), "Test lesson", "Test description", 15L, c1, 5, t1, r1, r2 );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testAdd()
    {
        lessonRepository.save( l1 );
        assertNotNull( l1.getId() );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testUpdate()
    {
        lessonRepository.save( l1 );

        assertNotNull( l1.getId() );

        l1.setDuration( 90L );

        Lesson l3 = lessonRepository.findOne( l1.getId() );

        assertEquals( l1, l3 );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testRemove()
    {
        lessonRepository.save( l1 );
        lessonRepository.delete( l1 );
        assertNull( lessonRepository.findOne( l1.getId() ) );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetById()
    {
        lessonRepository.save( l1 );

        assertNotNull( l1.getId() );
        assertNotNull( lessonRepository.findOne( l1.getId() ) );
    }

    @Test
    @Transactional( propagation = Propagation.REQUIRES_NEW )
    public void testGetAll()
    {
        lessonRepository.save( l1 );
        lessonRepository.save( l2 );

        assertNotNull( l1.getId() );
        assertNotNull( l2.getId() );

        Collection<Lesson> list = Utils.makeCollection( lessonRepository.findAll() );

        assertNotNull( list );
        assertEquals( 2, list.size() );
    }
}
