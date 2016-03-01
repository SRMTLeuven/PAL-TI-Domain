package be.peerassistedlearningti.model;

import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class StudentTest extends ValidationTest
{

    @Test
    public void testNameIsNull()
    {
        Student s = new Student( null, "paswoord", "davidopdebeeck@hotmail.com", Curriculum.TI, "david.op.de.beeck", UserType.ADMIN );

        Set<ConstraintViolation<Student>> constraintViolations = validator.validateProperty( s, "name" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Student name should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testPasswordIsNull()
    {
        Student s = new Student( "David", null, "davidopdebeeck@hotmail.com", Curriculum.TI, "david.op.de.beeck", UserType.ADMIN );

        Set<ConstraintViolation<Student>> constraintViolations = validator.validateProperty( s, "password" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Student password should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testSaltIsNull()
    {
        Student s = new Student();

        Set<ConstraintViolation<Student>> constraintViolations = validator.validateProperty( s, "salt" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Student salt should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testEmailIsNull()
    {
        Student s = new Student( "David", "paswoord", null, Curriculum.TI, "david.op.de.beeck", UserType.ADMIN );

        Set<ConstraintViolation<Student>> constraintViolations = validator.validateProperty( s, "email" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Student email should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testEmailIsNotValid()
    {
        Student s = new Student( "David", "paswoord", "davidopdebeeckhotmailcom", Curriculum.TI, "david.op.de.beeck", UserType.ADMIN );

        Set<ConstraintViolation<Student>> constraintViolations = validator.validateProperty( s, "email" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Student email should be valid!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testUserTypeIsNull()
    {
        Student s = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", Curriculum.TI, "david.op.de.beeck", null );

        Set<ConstraintViolation<Student>> constraintViolations = validator.validateProperty( s, "type" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Student type should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testSecurityTokenIsNull()
    {
        Student s = new Student();

        Set<ConstraintViolation<Student>> constraintViolations = validator.validateProperty( s, "securityToken" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Student security token should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testCurriculumIsNull()
    {
        Student s = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", null, "david.op.de.beeck", UserType.ADMIN );

        Set<ConstraintViolation<Student>> constraintViolations = validator.validateProperty( s, "curriculum" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Student curriculum should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

    @Test
    public void testProfileIdentifierIsNull()
    {
        Student s = new Student( "David", "paswoord", "davidopdebeeck@hotmail.com", Curriculum.TI, null, UserType.ADMIN );

        Set<ConstraintViolation<Student>> constraintViolations = validator.validateProperty( s, "profileIdentifier" );

        Assert.assertEquals( 1, constraintViolations.size() );
        Assert.assertEquals( "Student profile identifier should not be empty!", constraintViolations.iterator().next().getMessage() );
    }

}