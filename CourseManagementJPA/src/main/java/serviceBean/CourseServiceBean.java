package serviceBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import databaseApplication.jpa.Course;
import service.CourseService;

@ManagedBean(name = "courseServiceBean")
@RequestScoped
public class CourseServiceBean {
	@ManagedProperty(value = "#{course}")
	private Course course;

	private String errMsg = null;
	private CourseService courseService;

	@ManagedProperty(value = "#{emFactoryBean}")
	private EntityManagerFactoryBean factoryBean;

	@PostConstruct
	public void init() {
		courseService = new CourseService(factoryBean);
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String addCourse() {
		courseService.addCourse(course);
		return "listCourse";
	}

	public List<Course> getCourses() {
		return courseService.getCourses();
	}
}