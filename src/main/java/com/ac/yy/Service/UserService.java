package com.ac.yy.Service;

import com.ac.yy.DTO.*;
import com.ac.yy.Entity.*;
import com.ac.yy.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {
    @Autowired UserRepository userRepository;
    @Autowired StudentRepository studentRepository;
    @Autowired AcademicRepository academicRepository;
    @Autowired PositionRepository positionRepository;
    @Autowired CourseRepository courseRepository;
    @Autowired SubjectRepository subjectRepository;
    @Autowired AttendanceRepository attendanceRepository;
    @Autowired CourseBoardRepository courseBoardRepository;
    @Autowired CourseQuestionRepository courseQuestionRepository;
    @Autowired SubjectQuestionRepository subjectQuestionRepository;
    @Autowired SubjectBoardRepository subjectBoardRepository;
    @Autowired AdmissionQuestionRepository admissionQuestionRepository;
    @Autowired CourseAnswerRepository courseAnswerRepository;
    @Autowired SubjectAnswerRepository subjectAnswerRepository;
    @Autowired AdmissionAnswerRepository admissionAnswerRepository;
    @Autowired HomeworkRepository homeworkRepository;
    @Autowired LectureRepository lectureRepository;
    @Autowired SubmitRepository submitRepository;
    @Autowired FeedbackRepository feedbackRepository;
    @Autowired StudyRepository studyRepository;
    public ResponseDTO<?> getUserByUid(int uid) {
        StudentDTO studentDTO = new StudentDTO();
        AcademicDTO academicDTO = new AcademicDTO();
        UserEntity user = null;

        try {
            user = userRepository.findByUid(uid).get();
            user.setUserPw("");

            boolean isStudentExist = studentRepository.existsByUid(user.getUid());
            boolean isAcademicExist = academicRepository.existsByUid(user.getUid());

            if(isStudentExist) {
                studentDTO.setUser(user);
                studentDTO.setStudent(studentRepository.findByUid(user.getUid()).get());
                System.out.println(studentDTO);
                return ResponseDTO.setSuccess("Student Load Success!", studentDTO);
            }
            if(isAcademicExist) {
                academicDTO.setUser(user);
                academicDTO.setAcademic(academicRepository.findByUid(user.getUid()).get());
                academicDTO.setPosition(positionRepository.findById(academicDTO.getAcademic().getPosition()).get().getPositionName());
                if(academicDTO.getAcademic().getDept() == 0) {
                    academicDTO.setNum(courseRepository.findByAcademicId(academicDTO.getAcademic().getAcademicId()).size());
                }
                else {
                    academicDTO.setNum(subjectRepository.findByAcademicId(academicDTO.getAcademic().getAcademicId()).size());
                }
                System.out.println(academicDTO);
                return ResponseDTO.setSuccess("Academic Load Success!", academicDTO);
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("User Load Success!", user);
    }

    public ResponseDTO<?> getStudentsByCourseId(int id) {
        List<StudentDTO> students = new ArrayList<StudentDTO>();
        List<StudentEntity> temp = new ArrayList<StudentEntity>();

        try {
            temp = studentRepository.findByCourseId(id);
            temp.forEach(data -> {
                StudentDTO tempStudentDTO = new StudentDTO();
                tempStudentDTO.setStudent(data);
                tempStudentDTO.setUser(userRepository.findByUid(data.getUid()).get());
                students.add(tempStudentDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Students Load Success!", students);
    }

    public ResponseDTO<?> getStudentsBySubjectId(int id) {
        List<StudentDTO> students = new ArrayList<StudentDTO>();
        List<StudentEntity> temp = new ArrayList<StudentEntity>();

        try {
            int courseId = subjectRepository.findById(id).get().getCourseId();
            temp = studentRepository.findByCourseId(courseId);
            temp.forEach(data -> {
                StudentDTO tempStudentDTO = new StudentDTO();
                tempStudentDTO.setStudent(data);
                tempStudentDTO.setUser(userRepository.findByUid(data.getUid()).get());
                students.add(tempStudentDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Students Load Success!", students);
    }

    public ResponseDTO<?> getAcademicsByDept(int dept) {
        List<AcademicDTO> academics = new ArrayList<AcademicDTO>();
        List<AcademicEntity> temp = new ArrayList<AcademicEntity>();

        try {
            temp = academicRepository.findByDept(dept);
            temp.forEach(data -> {
                AcademicDTO tempAcademicDTO = new AcademicDTO();
                tempAcademicDTO.setAcademic(data);
                tempAcademicDTO.setUser(userRepository.findByUid(data.getUid()).get());
                PositionEntity tempPositionEntity = positionRepository.findById(tempAcademicDTO.getAcademic().getPosition()).get();
                tempAcademicDTO.setPosition(tempPositionEntity.getPositionName());
                if(data.getDept() == 0) {
                    tempAcademicDTO.setNum(courseRepository.findByAcademicId(data.getAcademicId()).size());
                }
                else {
                    tempAcademicDTO.setNum(subjectRepository.findByAcademicId(data.getAcademicId()).size());
                }
                academics.add(tempAcademicDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Academics Load Success!", academics);
    }

    public ResponseDTO<?> getStudentByStudentId(int id) {
        StudentDTO student = new StudentDTO();
        StudentEntity temp = null;

        try {
            temp = studentRepository.findByStudentId(id).get();
            student.setStudent(temp);
            student.setUser(userRepository.findByUid(temp.getUid()).get());
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Student Load Success!", student);
    }

    public ResponseDTO<?> getAcademicByAcademicId(int id) {
        AcademicDTO academic = new AcademicDTO();
        AcademicEntity temp = null;

        try {
            temp = academicRepository.findByAcademicId(id).get();
            academic.setAcademic(temp);
            academic.setUser(userRepository.findByUid(temp.getUid()).get());
            PositionEntity tempPositionEntity = positionRepository.findById(academic.getAcademic().getPosition()).get();
            academic.setPosition(tempPositionEntity.getPositionName());
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Academic Load Success!", academic);
    }

    public ResponseDTO<?> getUserByUserId(String userId) {
        UserEntity userEntity = null;

        try {
            userEntity = userRepository.findByUserId(userId).get();
            userEntity.setUserPw("");
            boolean isStudent = studentRepository.existsByUid(userEntity.getUid());
            boolean isAcademic = academicRepository.existsByUid(userEntity.getUid());
            if(isStudent || isAcademic) {
                userEntity = null;
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("User Load Success!", userEntity);
    }

    public ResponseDTO<?> changeAvailable(int id, int value) {
        if (value == 0) {
            value = 1;
        } else {value = 0;}

        try {
            userRepository.modifyingAvailableByUid(id, value);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("ChangeAvailable Success!", null);
    }

    public ResponseDTO<?> mod(AcademicAdminDTO dto) {
            int position;
        try {
            position = positionRepository.findByPositionName(dto.getUserPosition()).get().getPositionId();
            academicRepository.modifyingInfoByUid(dto.getUid(), dto.getUserAuth(), dto.getUserDept(), position, dto.getUserRemark(), dto.getUserAvailable());
            userRepository.modifyingInfoByUid(dto.getUid(), dto.getUserName(), dto.getUserBirth(), dto.getUserAddr(), dto.getUserPhone(), dto.getUserEmail());
        }
        catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Mod Success!", null);
    }

    public ResponseDTO<?> add(AcademicAdminDTO dto) {
        try {
            int position = positionRepository.findByPositionName(dto.getUserPosition()).get().getPositionId();
            String savePath = System.getProperty("user.dir") + "\\images\\UserDefault.png";
            AcademicEntity academic = new AcademicEntity(dto);
            academic.setPosition(position);
            academic.setUserPhoto(savePath);
            academicRepository.save(academic);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Add Success!", dto);
    }

    public ResponseDTO<?> studentUpdate(int id, StudentUpdateDTO dto) {
        try {
            UserEntity userEntity = userRepository.findByUid(id).get();
            userEntity.setUserAddr(dto.getUserAddr());
            userEntity.setUserEmail(dto.getUserEmail());
            userEntity.setUserPhone(dto.getUserPhone());
            userRepository.save(userEntity);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Student Detail Update Success!", null);
    }

    public ResponseDTO<?> getStudentAttendanceByStudentId(int id) {
        List<AttendanceEntity> attendance = new ArrayList<AttendanceEntity>();
        try {
            attendance = attendanceRepository.findByStudentIdOrderByAttendDateDesc(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Student Attendance Load Success!", attendance);
    }

    public ResponseDTO<?> getAllStudents() {
        List<StudentDTO> students = new ArrayList<StudentDTO>();
        List<StudentEntity> temp = new ArrayList<StudentEntity>();

        try {
            temp = studentRepository.findAll();
            temp.forEach(data -> {
                StudentDTO tempStudentDTO = new StudentDTO();
                tempStudentDTO.setStudent(data);
                tempStudentDTO.setUser(userRepository.findByUid(data.getUid()).get());
                tempStudentDTO.getUser().setUserPw("");
                students.add(tempStudentDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Students Load Success!", students);
    }

    public ResponseDTO<?> getAllPosts(int id, int type) {
        List<MyPostDTO> posts = new ArrayList<MyPostDTO>();
        try {
            // student
            if(type == 0) {
                List<CourseQuestionEntity> courseQuestions = courseQuestionRepository.findByStudentIdOrderByRegDateDesc(id);
                List<SubjectQuestionEntity> subjectQuestions = subjectQuestionRepository.findByStudentIdOrderByRegDateDesc(id);
                for (CourseQuestionEntity courseQuestion : courseQuestions) {
                    MyPostDTO temp = new MyPostDTO();
                    temp.setId(courseQuestion.getCourseQuestionId());
                    temp.setType("cqna");
                    temp.setTitle(courseQuestion.getTitle());
                    temp.setContent(courseQuestion.getContent());
                    temp.setRegDate(courseQuestion.getRegDate());
                    posts.add(temp);
                }
                for (SubjectQuestionEntity data : subjectQuestions) {
                    MyPostDTO temp = new MyPostDTO();
                    temp.setId(data.getSubjectQuestionId());
                    temp.setType(data.getSubjectId() + "/sqna");
                    temp.setTitle(data.getTitle());
                    temp.setContent(data.getContent());
                    temp.setRegDate(data.getRegDate());
                    posts.add(temp);
                }
                posts.sort((o1, o2) -> o2.getRegDate().compareTo(o1.getRegDate()));
            }
            // academic
            else if(type == 1) {
                AcademicEntity user = academicRepository.findByAcademicId(id).get();
                // manager
                if(user.getDept() == 0) {
                    List<CourseBoardEntity> courseBoard = courseBoardRepository.findByAcademicId(id);
                    for (CourseBoardEntity data : courseBoard) {
                        MyPostDTO temp = new MyPostDTO();
                        temp.setId(data.getCourseBoardId());
                        temp.setType(data.getCourseId() + "/board");
                        temp.setTitle(data.getTitle());
                        temp.setContent(data.getContent());
                        temp.setRegDate(data.getRegDate());
                        posts.add(temp);
                    }
                    posts.sort((o1, o2) -> o2.getRegDate().compareTo(o1.getRegDate()));
                }
                // trainer
                else if(user.getDept() == 1) {
                    List<SubjectBoardEntity> subjectBoard = subjectBoardRepository.findByAcademicId(id);
                    List<LectureEntity> lectures = lectureRepository.findByAcademicId(id);
                    List<HomeworkEntity> homeworks = homeworkRepository.findByAcademicId(id);
                    for (SubjectBoardEntity subjectBoardEntity : subjectBoard) {
                        MyPostDTO temp = new MyPostDTO();
                        temp.setId(subjectBoardEntity.getSubjectBoardId());
                        temp.setType(subjectBoardEntity.getSubjectId() + "/board");
                        temp.setTitle(subjectBoardEntity.getTitle());
                        temp.setContent(subjectBoardEntity.getContent());
                        temp.setRegDate(subjectBoardEntity.getRegDate());
                        posts.add(temp);
                    }
                    for (LectureEntity lecture : lectures) {
                        MyPostDTO temp = new MyPostDTO();
                        temp.setId(lecture.getLectureId());
                        temp.setType(lecture.getSubjectId() + "/lecture");
                        temp.setTitle(lecture.getTitle());
                        temp.setContent(lecture.getContent());
                        temp.setRegDate(lecture.getRegDate());
                        posts.add(temp);
                    }
                    for (HomeworkEntity data : homeworks) {
                        MyPostDTO temp = new MyPostDTO();
                        temp.setId(data.getHomeworkId());
                        temp.setType(data.getSubjectId() + "/homework");
                        temp.setTitle(data.getTitle());
                        temp.setContent(data.getContent());
                        temp.setRegDate(data.getRegDate());
                        posts.add(temp);
                    }
                    posts.sort((o1, o2) -> o2.getRegDate().compareTo(o1.getRegDate()));
                }
                else {
                    return ResponseDTO.setFailed("Error");
                }
            }
            else {
                return ResponseDTO.setFailed("Error");
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("My Posts Load Success!", posts);
    }

    public ResponseDTO<?> getAllReplies(int id) {
        List<MyReplyDTO> replies = new ArrayList<MyReplyDTO>();
        try {
            AcademicEntity user = academicRepository.findByAcademicId(id).get();
            // manager
            if(user.getDept() == 0) {
                List<CourseAnswerEntity> courseAnswers = courseAnswerRepository.findByAcademicIdOrderByAnswerRegDateDesc(id);
                List<AdmissionAnswerEntity> admissionAnswers = admissionAnswerRepository.findByAcademicId(id);
                for (CourseAnswerEntity data : courseAnswers) {
                    MyReplyDTO temp = new MyReplyDTO();
                    temp.setId(data.getCourseQuestionId());
                    temp.setType(courseQuestionRepository.findById(data.getCourseQuestionId()).get().getCourseId() + "/qna");
                    temp.setTitle(courseQuestionRepository.findById(data.getCourseQuestionId()).get().getTitle());
                    temp.setContent(data.getAnswerContent());
                    temp.setRegDate(data.getAnswerRegDate());
                    replies.add(temp);
                }
                for (AdmissionAnswerEntity data : admissionAnswers) {
                    MyReplyDTO temp = new MyReplyDTO();
                    temp.setId(data.getAdmissionQuestionId());
                    temp.setType("admission");
                    temp.setTitle(admissionQuestionRepository.findById(data.getAdmissionQuestionId()).get().getTitle());
                    temp.setContent(data.getAnswerContent());
                    temp.setRegDate(data.getAnswerRegDate());
                    replies.add(temp);
                }
                replies.sort((o1, o2) -> o2.getRegDate().compareTo(o1.getRegDate()));
            }
            // trainer
            if(user.getDept() == 1) {
                List<SubjectAnswerEntity> subjectAnswers = subjectAnswerRepository.findByAcademicIdOrderByAnswerRegDateDesc(id);
                for (SubjectAnswerEntity data : subjectAnswers) {
                    MyReplyDTO temp = new MyReplyDTO();
                    temp.setId(data.getSubjectQuestionId());
                    temp.setType(subjectQuestionRepository.findById(data.getSubjectQuestionId()).get().getSubjectId() + "/qna");
                    temp.setTitle(subjectQuestionRepository.findById(data.getSubjectQuestionId()).get().getTitle());
                    temp.setContent(data.getAnswerContent());
                    temp.setRegDate(data.getAnswerRegDate());
                    replies.add(temp);
                }
                replies.sort((o1, o2) -> o2.getRegDate().compareTo(o1.getRegDate()));
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("My Replies Load Success!", replies);
    }

    public ResponseDTO<?> getScoreByStudentId(int id) {
        StudentScoreDTO studentScore = new StudentScoreDTO();
        List<StudentSubjectScoreDTO> subjectScores = new ArrayList<StudentSubjectScoreDTO>();
        try {
            StudentEntity student = studentRepository.findByStudentId(id).get();
            studentScore.setCourse(courseRepository.findById(student.getCourseId()).get());

            List<SubjectEntity> subjects = subjectRepository.findByCourseId(studentScore.getCourse().getCourseId());
            subjects.forEach(data -> {
                StudentSubjectScoreDTO temp = new StudentSubjectScoreDTO();
                temp.setSubject(data);

                // 과목별 과제 점수
                List<HomeworkEntity> homeworks = homeworkRepository.findBySubjectIdOrderByStartDateDesc(data.getSubjectId());
                AtomicInteger tempScore = new AtomicInteger(0);
                homeworks.forEach(hw -> {
                    if(submitRepository.existsByStudentIdAndHomeworkId(id, hw.getHomeworkId())) {
                        SubmitEntity submit = submitRepository.findByStudentIdAndHomeworkId(id, hw.getHomeworkId()).get();
                        if(feedbackRepository.existsById(submit.getSubmitId())) {
                            FeedbackEntity feedback = feedbackRepository.findById(submit.getSubmitId()).get();
                            tempScore.set(tempScore.get() + feedback.getHwScore());
                        }
                    }
                });
                temp.setHomework(Math.round((float) tempScore.get() / homeworks.size()));

                // 과목별 강의 점수
                List<LectureEntity> lectures = lectureRepository.findBySubjectId(data.getSubjectId());
                tempScore.set(0);
                lectures.forEach(l -> {
                    if(studyRepository.existsByStudentIdAndLectureId(id, l.getLectureId())) {
                        StudyEntity study = studyRepository.findByStudentIdAndLectureId(id, l.getLectureId()).get();
                        if(study.getIsStudy() == 1) {
                            tempScore.set(tempScore.get() + 1);
                        }
                    }
                });
                temp.setLecture(Math.round((float) tempScore.get() / lectures.size() * 100));
                subjectScores.add(temp);
            });

            studentScore.setSubjectScore(subjectScores);

            // 총 강의 점수
            // 총 과제 점수
            AtomicInteger tempLecture = new AtomicInteger(0);
            AtomicInteger tempHomework = new AtomicInteger(0);
            subjectScores.forEach(data -> {
                tempLecture.set(tempLecture.get() + data.getLecture());
                tempHomework.set(tempHomework.get() + data.getHomework());
            });
            tempLecture.set(Math.round((float) tempLecture.get() / subjectScores.size()));
            tempHomework.set(Math.round((float)tempHomework.get() / subjectScores.size()));

            studentScore.setLecture(tempLecture.get());
            studentScore.setHomework(tempHomework.get());

            // 과정 일수(평일) 계산
            String startDate = studentScore.getCourse().getStartDate();
            String endDate = studentScore.getCourse().getEndDate();

            LocalDate start = LocalDate.of(Integer.parseInt(startDate.split("-")[0]), Integer.parseInt(startDate.split("-")[1]), Integer.parseInt(startDate.split("-")[2]));
            LocalDate end = LocalDate.of(Integer.parseInt(endDate.split("-")[0]), Integer.parseInt(endDate.split("-")[1]), Integer.parseInt(endDate.split("-")[2]));
            LocalDate temp = start;

            long days = ChronoUnit.DAYS.between(start, end);
            System.out.println("1: " + days);

            int period = 0;
            for (int i = 0; i <= days; i++) {
                DayOfWeek day = temp.getDayOfWeek();
                System.out.println(day);
                int number = day.getValue();
                if(number != 6 && number != 7) {
                    period++;
                }
                temp = temp.plusDays(1);
            }

            // 출석 점수
            AtomicInteger attendCount = new AtomicInteger(0);
            List<AttendanceEntity> attendance = attendanceRepository.findByStudentIdOrderByAttendDateDesc(id);
            attendance.forEach(data -> {
                if(data.getAbsenceId() < 99) {
                    attendCount.set(attendCount.get() + 1);
                }
            });
            studentScore.setAttendance(Math.round((float) attendCount.get() / period * 100));

            studentScore.setTotal((float) ((studentScore.getAttendance() * 0.2) + (studentScore.getHomework() * 0.4) + (studentScore.getLecture() * 0.4)));
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Student's score Load Success!", studentScore);
    }
}