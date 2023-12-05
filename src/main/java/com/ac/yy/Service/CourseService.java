package com.ac.yy.Service;

import com.ac.yy.DTO.*;
import com.ac.yy.Entity.*;
import com.ac.yy.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired CourseRepository courseRepository;
    @Autowired CourseBoardRepository courseBoardRepository;
    @Autowired CourseQuestionRepository courseQuestionRepository;
    @Autowired CourseAnswerRepository courseAnswerRepository;
    @Autowired StudentRepository studentRepository;
    @Autowired CourseReviewRepository courseReviewRepository;
    @Autowired SubjectRepository subjectRepository;
    public ResponseDTO<?> getCourseById(int id) {
        CourseEntity course = null;

        try {
            course = courseRepository.findById(id).get();
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Course Load Success!", course);
    }

    public ResponseDTO<?> getCourseByAcademicId(int id) {
        List<CourseEntity> courses;

        try {
            courses = courseRepository.findByAcademicId(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Courses Load Success!", courses);
    }

    public ResponseDTO<?> getRecruitingCourses() {
        List<CourseEntity> courses = new ArrayList<CourseEntity>();
        LocalDate now = LocalDate.now();

        try {
            List<CourseEntity> temp = courseRepository.findAll();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            temp.forEach(data -> {
                LocalDate start = LocalDate.parse(data.getRecruitStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate end = LocalDate.parse(data.getRecruitEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                if((now.compareTo(start) >= 0) && (now.compareTo(end) <= 0)) {
                    courses.add(data);
                }
            });

        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Recruiting Courses Load Success!", courses);
    }

    public ResponseDTO<?> getAllCourses() {
        List<CourseEntity> courses = new ArrayList<CourseEntity>();
        try {
            courses = courseRepository.findAll();
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("All Courses Load Success!", courses);
    }

    public ResponseDTO<?> getBoardByCourseId(int id) {
        List<CourseBoardEntity> board = new ArrayList<CourseBoardEntity>();
        try {
            board = courseBoardRepository.findByCourseIdOrderByRegDateDesc(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Course Board Load Success!", board);
    }

    public ResponseDTO<?> getBoardByAcademicId(int id) {
        List<CourseBoardEntity> board = new ArrayList<CourseBoardEntity>();
        try {
            board = courseBoardRepository.findByAcademicId(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Course Board Load Success!", board);
    }

    public ResponseDTO<?> getBoardByCourseBoardId(int id) {
        CourseBoardEntity post = null;
        try {
            courseBoardRepository.modifyingHitsByCourseBoardId(id);
            post = courseBoardRepository.findById(id).get();
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Course Board Post Load Success!", post);
    }

    public ResponseDTO<?> getQnaByCourseId(int id) {
        List<CourseQnaDTO> courseQnaDTO = new ArrayList<CourseQnaDTO>();
        List<CourseQuestionEntity> temp = new ArrayList<CourseQuestionEntity>();
        try {
            temp = courseQuestionRepository.findByCourseIdOrderByRegDateDesc(id);
            temp.forEach(data -> {
                CourseQnaDTO tempCourseQnaDTO = new CourseQnaDTO();
                CourseAnswerEntity answer = null;
                if(courseAnswerRepository.existsById(data.getCourseQuestionId())) {
                    answer = courseAnswerRepository.findById(data.getCourseQuestionId()).get();
                }
                tempCourseQnaDTO.setQuestion(data);
                tempCourseQnaDTO.setAnswer(answer);
                courseQnaDTO.add(tempCourseQnaDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Qnas Load Success!", courseQnaDTO);
    }

    public ResponseDTO<?> getQnaByCourseQuestionId(int id) {
        CourseQnaDTO courseQnaDTO = new CourseQnaDTO();
        try {
            CourseQuestionEntity question = courseQuestionRepository.findById(id).get();
            CourseAnswerEntity answer = null;
            if(courseAnswerRepository.existsById(id)) {
                answer = courseAnswerRepository.findById(id).get();
            }
            courseQnaDTO.setQuestion(question);
            courseQnaDTO.setAnswer(answer);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Qna Post Load Success!", courseQnaDTO);
    }

    public ResponseDTO<?> getQnaByStudentId(int id) {
        List<CourseQnaDTO> courseQnaDTO = new ArrayList<CourseQnaDTO>();
        List<CourseQuestionEntity> temp = new ArrayList<CourseQuestionEntity>();
        try {
            temp = courseQuestionRepository.findByStudentIdOrderByRegDateDesc(id);
            temp.forEach(data -> {
                CourseQnaDTO tempCourseQnaDTO = new CourseQnaDTO();
                CourseAnswerEntity answer = null;
                if(courseAnswerRepository.existsById(data.getCourseQuestionId())) {
                    answer = courseAnswerRepository.findById(data.getCourseQuestionId()).get();
                }
                tempCourseQnaDTO.setQuestion(data);
                tempCourseQnaDTO.setAnswer(answer);
                courseQnaDTO.add(tempCourseQnaDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Qnas Load Success!", courseQnaDTO);
    }

    public ResponseDTO<?> getQnaByAcademicId(int id) {
        List<CourseQnaDTO> courseQnaDTO = new ArrayList<CourseQnaDTO>();
        List<CourseAnswerEntity> temp = new ArrayList<CourseAnswerEntity>();
        try {
            temp = courseAnswerRepository.findByAcademicIdOrderByAnswerRegDateDesc(id);
            temp.forEach(data -> {
                CourseQnaDTO tempCourseQnaDTO = new CourseQnaDTO();
                CourseQuestionEntity question = courseQuestionRepository.findById(data.getCourseQuestionId()).get();
                tempCourseQnaDTO.setQuestion(question);
                tempCourseQnaDTO.setAnswer(data);
                courseQnaDTO.add(tempCourseQnaDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Qnas Load Success!", courseQnaDTO);
    }
    public ResponseDTO<?> getBoardBySearch(String keyword, int type, int id) {
        List<CourseBoardEntity> posts = new ArrayList<CourseBoardEntity>();
        List<CourseBoardEntity> courseBoardBySearch = new ArrayList<CourseBoardEntity>();
        try {
            List<CourseBoardEntity> courseBoardByCourseId = courseBoardRepository.findByCourseIdOrderByRegDateDesc(id);
            // 제목으로 검색
            if(type == 1 || type == 0) {
                courseBoardBySearch = courseBoardRepository.findByTitleContainingOrderByRegDateDesc(keyword);
                List<CourseBoardEntity> temp = courseBoardBySearch.stream().filter(o -> courseBoardByCourseId.stream().anyMatch(Predicate.isEqual(o))).collect(Collectors.toList());
                posts.addAll(temp);
            }
            // 내용으로 검색
            if(type == 2 || type == 0) {
                courseBoardBySearch = courseBoardRepository.findByContentContainingOrderByRegDateDesc(keyword);
                List<CourseBoardEntity> temp = courseBoardBySearch.stream().filter(o -> courseBoardByCourseId.stream().anyMatch(Predicate.isEqual(o))).collect(Collectors.toList());
                posts.addAll(temp);
            }
            if(type == 0) {
                Collections.sort(posts, (o1, o2) -> o2.getRegDate().compareTo(o1.getRegDate()));
                Set<CourseBoardEntity> tempPosts = new HashSet<>(posts);
                List<CourseBoardEntity> result = new ArrayList<>(tempPosts);
                return ResponseDTO.setSuccess("Searched Course Board Search Success!", result);
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Searched Course Board Search Success!", posts);
    }

    public ResponseDTO<?> writeCourseQuestion(CourseQuestionWriteDTO dto) {
        CourseQuestionEntity courseQuestionEntity = new CourseQuestionEntity(dto);
        try {
            courseQuestionEntity.setCourseId(studentRepository.findByStudentId(dto.getStudentId()).get().getCourseId());
            courseQuestionRepository.save(courseQuestionEntity);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Write Course Question Success!", null);
    }

    public ResponseDTO<?> writeCourseQuestion(int id, CourseQuestionWriteDTO dto) {
        try {
            CourseQuestionEntity courseQuestionEntity = courseQuestionRepository.findById(id).get();
            courseQuestionEntity.setTitle(dto.getTitle());
            courseQuestionEntity.setContent(dto.getContent());
            courseQuestionRepository.save(courseQuestionEntity);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Write Course Question Success!", null);
    }

    public ResponseDTO<?> writeReview(ReviewDTO dto) {
        CourseReviewEntity courseReviewEntity = new CourseReviewEntity(dto);
        try {
            courseReviewRepository.save(courseReviewEntity);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Review Write Success!", null);
    }

    public ResponseDTO<?> getSubjectReviewByCourseId(int id) {
        List<CourseReviewEntity> reviews = new ArrayList<CourseReviewEntity>();
        try {
            List<SubjectEntity> subjects = subjectRepository.findByCourseId(id);
            subjects.forEach(data -> {
                List<CourseReviewEntity> temp = courseReviewRepository.findBySubjectIdOrderByReviewScoreDesc(data.getSubjectId());
                reviews.addAll(temp);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Subject Reviews in Course Load Success!", reviews);
    }

    public ResponseDTO<?> getSubjectReviewByStudentId(int id) {
        List<CourseReviewEntity> reviews = new ArrayList<CourseReviewEntity>();
        try {
            StudentEntity student = studentRepository.findByStudentId(id).get();
            List<SubjectEntity> subjects = subjectRepository.findByCourseId(student.getCourseId());
            subjects.forEach(data -> {
                if(courseReviewRepository.existsByStudentIdAndSubjectId(id, data.getSubjectId())) {
                    CourseReviewEntity temp = courseReviewRepository.findByStudentIdAndSubjectId(id, data.getSubjectId()).get();
                    reviews.add(temp);
                }
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Student's Subject Reviews Load Success!", reviews);
    }

    public ResponseDTO<?> add(CourseDTO dto) {
        try {
            CourseEntity course = new CourseEntity(dto);
            String savePath = System.getProperty("user.dir") + "\\images\\CourseDefault.png";
            course.setCoursePhoto(savePath);
            courseRepository.save(course);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Course Add Success!", null);
    }
}
