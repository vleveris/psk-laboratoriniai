package lab1.rest;

import lab1.jpa.entities.Course;
import lab1.jpa.entities.Lecturer;
import lab1.jpa.entities.University;
import lab1.jpa.persistence.CoursesDAO;
import lab1.jpa.persistence.LecturersDAO;
import lab1.jpa.persistence.UniversitiesDAO;
import lab1.rest.contracts.CourseDTO;
import lab1.rest.contracts.LecturerDTO;
import lab1.rest.contracts.UniversityDTO;
import lab1.services.CourseGenerator;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("/courses")
public class CoursesController {
    @Inject
    @Setter
    @Getter
    private CoursesDAO coursesDAO;

    @Inject
    @Setter
    @Getter
    private LecturersDAO lecturersDAO;

    @Inject
    @Setter
    @Getter
    private UniversitiesDAO universitiesDAO;

    @Inject
    private CourseGenerator courseGenerator;

    @Context
    private UriInfo uriInfo;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final int id) {
        Course course = coursesDAO.findById(id);
        if (course == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setTitle(course.getTitle());
        courseDTO.setNumberOfStudents(course.getNumberOfStudents());

        LecturerDTO lecturerDTO = new LecturerDTO();
        lecturerDTO.setId(course.getLecturer().getId());
        lecturerDTO.setFirstName(course.getLecturer().getFirstName());
        lecturerDTO.setLastName(course.getLecturer().getLastName());
        lecturerDTO.setName(course.getLecturer().getName());
        courseDTO.setLecturer(lecturerDTO);

        UniversityDTO universityDTO = new UniversityDTO();
        universityDTO.setId(course.getUniversity().getId());
        universityDTO.setName(course.getUniversity().getName());

        courseDTO.setUniversity(universityDTO);
        return Response.ok(courseDTO).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(@NotNull CourseDTO courseDTO) {
        Lecturer l = lecturersDAO.findById(courseDTO.getLecturer().getId());
        if (l == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Course must include a valid lecturer id").build();
        }
        University u = universitiesDAO.findById(courseDTO.getUniversity().getId());
        if (u == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Course must include a valid university id").build();
        }
        u.getCoursesWithLecturers().add(l);
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setNumberOfStudents(courseGenerator.generateId());
        course.setLecturer(l);
        course.setUniversity(u);
        coursesDAO.persist(course);
        return Response.created(uriInfo.getRequestUri().resolve(String.valueOf(course.getId()))).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final int courseId,
            @NotNull CourseDTO courseDTO) {
        try {
            Course existingCourse = coursesDAO.findById(courseId);
            if (existingCourse == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Response.status(Response.Status.NOT_FOUND).build();

            }

            Lecturer l = lecturersDAO.findById(courseDTO.getLecturer().getId());
            if (l == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Course must include a valid lecturer id").build();
            }
            University u = universitiesDAO.findById(courseDTO.getUniversity().getId());
            if (u == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Course must include a valid university id").build();
            }
            u.getCoursesWithLecturers().add(l);
            existingCourse.setTitle(courseDTO.getTitle());
            existingCourse.setLecturer(l);
            existingCourse.setUniversity(u);
            existingCourse.setNumberOfStudents(courseDTO.getNumberOfStudents());

            coursesDAO.update(existingCourse);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
