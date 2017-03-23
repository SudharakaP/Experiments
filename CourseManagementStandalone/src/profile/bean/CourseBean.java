package profile.bean;

import java.util.List;

import profile.dto.CourseDTO;
import profile.service.CourseService;

public class CourseBean {
  private CourseService courseService = new CourseService();
  
  public List<CourseDTO> getCourses() {
    return courseService.getCourses();
  }
}