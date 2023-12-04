package com.ac.yy.Service;

import com.ac.yy.DTO.*;
import com.ac.yy.Entity.*;
import com.ac.yy.Repository.*;
import javafx.scene.effect.SepiaTone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    @Autowired SubjectRepository subjectRepository;
    @Autowired CourseRepository courseRepository;
    @Autowired SubjectBoardRepository subjectBoardRepository;
    @Autowired HomeworkRepository homeworkRepository;
    @Autowired SubmitRepository submitRepository;
    @Autowired FeedbackRepository feedbackRepository;
    @Autowired LectureRepository lectureRepository;
    @Autowired StudyRepository studyRepository;
    @Autowired SubjectQuestionRepository subjectQuestionRepository;
    @Autowired SubjectAnswerRepository subjectAnswerRepository;
    @Autowired StudentRepository studentRepository;
    @Autowired UserRepository userRepository;
    public ResponseDTO<?> getSubjectById(int id) {
        SubjectDTO subject = new SubjectDTO();
        SubjectEntity subjectTemp = null;
        CourseEntity courseTemp= null;

        try {
            subjectTemp = subjectRepository.findById(id).get();
            courseTemp = courseRepository.findById(subjectTemp.getCourseId()).get();
            subject.setSubject(subjectTemp);
            subject.setCourse(courseTemp);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Subject Load Success!", subject);
    }

    public ResponseDTO<?> getSubjectByAcademicId(int id) {
        List<SubjectDTO> subjects = new ArrayList<SubjectDTO>();
        List<SubjectEntity> temp = new ArrayList<SubjectEntity>();

        try {
            temp = subjectRepository.findByAcademicId(id);
            temp.forEach(data -> {
                SubjectDTO tempSubjectDTO = new SubjectDTO();
                tempSubjectDTO.setSubject(data);
                tempSubjectDTO.setCourse(courseRepository.findById(data.getCourseId()).get());
                subjects.add(tempSubjectDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Subjects Load Success!", subjects);
    }

    public ResponseDTO<?> getSubjectByCourseId(int id) {
        List<SubjectDTO> subjects = new ArrayList<SubjectDTO>();
        List<SubjectEntity> temp = new ArrayList<SubjectEntity>();

        try {
            temp = subjectRepository.findByCourseId(id);
            temp.forEach(data -> {
                SubjectDTO tempSubjectDTO = new SubjectDTO();
                tempSubjectDTO.setSubject(data);
                tempSubjectDTO.setCourse(courseRepository.findById(id).get());
                subjects.add(tempSubjectDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Subjects Load Success!", subjects);
    }

    public ResponseDTO<?> getBoardBySubjectId(int id) {
        List<SubjectBoardEntity> board = new ArrayList<SubjectBoardEntity>();
        try {
            board = subjectBoardRepository.findBySubjectIdOrderByRegDateDesc(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Subject Board Load Success!", board);
    }

    public ResponseDTO<?> getBoardByAcademicId(int id) {
        List<SubjectBoardEntity> board = new ArrayList<SubjectBoardEntity>();
        try {
            board = subjectBoardRepository.findByAcademicId(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Subject Board Load Success!", board);
    }

    public ResponseDTO<?> getBoardBySubjectBoardId(int id) {
        SubjectBoardEntity post = null;
        try {
            subjectBoardRepository.modifyingHitsBySubjectBoardId(id);
            post = subjectBoardRepository.findById(id).get();
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Subject Board Post Load Success!", post);
    }

    public ResponseDTO<?> getHomeworksBySubjectId(int id) {
        List<HomeworkEntity> homeworks = new ArrayList<HomeworkEntity>();
        try {
            homeworks = homeworkRepository.findBySubjectIdOrderByStartDateDesc(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Homeworks Load Success!", homeworks);
    }

    public ResponseDTO<?> getHomeworksByAcademicId(int id) {
        List<HomeworkEntity> homeworks = new ArrayList<HomeworkEntity>();
        try {
            homeworks = homeworkRepository.findByAcademicId(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Homeworks Load Success!", homeworks);
    }

    public ResponseDTO<?> getHomeworkByHomeworkId(int id) {
        HomeworkEntity post = null;
        try {
            post = homeworkRepository.findById(id).get();
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Homework Post Load Success!", post);
    }

    public ResponseDTO<?> getLecturesBySubjectId(int id) {
        List<LectureEntity> lectures = new ArrayList<LectureEntity>();
        try {
            lectures = lectureRepository.findBySubjectIdOrderByRegDateDesc(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Lectures Load Success!", lectures);
    }

    public ResponseDTO<?> getLecturesByAcademicId(int id) {
        List<LectureEntity> lectures = new ArrayList<LectureEntity>();
        try {
            lectures = lectureRepository.findByAcademicId(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Lectures Load Success!", lectures);
    }

    public ResponseDTO<?> getLectureByLectureId(int id) {
        LectureEntity post = null;
        try {
            lectureRepository.modifyingHitsByLectureId(id);
            post = lectureRepository.findById(id).get();
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Lecture Post Load Success!", post);
    }

    public ResponseDTO<?> getQnaBySubjectId(int id) {
        List<SubjectQnaDTO> subjectQnaDTO = new ArrayList<SubjectQnaDTO>();
        List<SubjectQuestionEntity> temp = new ArrayList<SubjectQuestionEntity>();
        try {
            temp = subjectQuestionRepository.findBySubjectIdOrderByRegDateDesc(id);
            temp.forEach(data -> {
                SubjectQnaDTO tempSubjectQnaDTO = new SubjectQnaDTO();
                SubjectAnswerEntity answer = null;
                if(subjectAnswerRepository.existsById(data.getSubjectQuestionId())) {
                    answer = subjectAnswerRepository.findById(data.getSubjectQuestionId()).get();
                }
                tempSubjectQnaDTO.setQuestion(data);
                tempSubjectQnaDTO.setAnswer(answer);
                subjectQnaDTO.add(tempSubjectQnaDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Qnas Load Success!", subjectQnaDTO);
    }

    public ResponseDTO<?> getQnaBySubjectQuestionId(int id) {
        SubjectQnaDTO subjectQnaDTO = new SubjectQnaDTO();
        try {
            subjectQuestionRepository.modifyingHitsBySubjectQuestionId(id);
            SubjectQuestionEntity question = subjectQuestionRepository.findById(id).get();
            SubjectAnswerEntity answer = null;
            if(subjectAnswerRepository.existsById(id)) {
                answer = subjectAnswerRepository.findById(id).get();
            }
            subjectQnaDTO.setQuestion(question);
            subjectQnaDTO.setAnswer(answer);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Qna Post Load Success!", subjectQnaDTO);
    }

    public ResponseDTO<?> getQnaByStudentId(int id) {
        List<SubjectQnaDTO> subjectQnaDTO = new ArrayList<SubjectQnaDTO>();
        List<SubjectQuestionEntity> temp = new ArrayList<SubjectQuestionEntity>();
        try {
            temp = subjectQuestionRepository.findByStudentIdOrderByRegDateDesc(id);
            temp.forEach(data -> {
                SubjectQnaDTO tempSubjectQnaDTO = new SubjectQnaDTO();
                SubjectAnswerEntity answer = null;
                if(subjectAnswerRepository.existsById(data.getSubjectQuestionId())) {
                    answer = subjectAnswerRepository.findById(data.getSubjectQuestionId()).get();
                }
                tempSubjectQnaDTO.setQuestion(data);
                tempSubjectQnaDTO.setAnswer(answer);
                subjectQnaDTO.add(tempSubjectQnaDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Qnas Load Success!", subjectQnaDTO);
    }

    public ResponseDTO<?> getQnaByAcademicId(int id) {
        List<SubjectQnaDTO> subjectQnaDTO = new ArrayList<SubjectQnaDTO>();
        List<SubjectAnswerEntity> temp = new ArrayList<SubjectAnswerEntity>();
        try {
            temp = subjectAnswerRepository.findByAcademicIdOrderByAnswerRegDateDesc(id);
            temp.forEach(data -> {
                SubjectQnaDTO tempSubjectQnaDTO = new SubjectQnaDTO();
                SubjectQuestionEntity question = subjectQuestionRepository.findById(data.getSubjectQuestionId()).get();
                tempSubjectQnaDTO.setQuestion(question);
                tempSubjectQnaDTO.setAnswer(data);
                subjectQnaDTO.add(tempSubjectQnaDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Qnas Load Success!", subjectQnaDTO);
    }

    public ResponseDTO<?> getHomeworksByCourseId(int id) {
        List<SubjectEntity> subjects = new ArrayList<SubjectEntity>();
        List<HomeworkEntity> homeworks = new ArrayList<HomeworkEntity>();
        try {
            subjects = subjectRepository.findByCourseId(id);
            subjects.forEach(data -> {
                homeworks.addAll(homeworkRepository.findBySubjectIdOrderByStartDateDesc(data.getSubjectId()));
            });
            Collections.sort(homeworks, (o1, o2) -> o2.getRegDate().compareTo(o1.getRegDate()));
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Homeworks Load Success!", homeworks);
    }

    public ResponseDTO<?> submitHomework(SubmitWriteDTO dto) {
        SubmitEntity submit = new SubmitEntity(dto);
        try {
            if(submitRepository.existsByStudentIdAndHomeworkId(submit.getStudentId(), submit.getHomeworkId())) {
                SubmitEntity temp = submitRepository.findByStudentIdAndHomeworkId(submit.getStudentId(), submit.getHomeworkId()).get();
                temp.setSubmitContent(submit.getSubmitContent());
                temp.setSubmitFileUrl(submit.getSubmitFileUrl());
                temp.setSubmitFileName(submit.getSubmitFileName());
                submitRepository.save(temp);
            }
            else {
                submitRepository.save(submit);
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Submit Success!", null);
    }

    public ResponseDTO<?> getSubmitBySubmitId(int id) {
        SubmitDTO submitDTO = new SubmitDTO();
        try {
            submitDTO.setSubmit(submitRepository.findById(id).get());
            if(feedbackRepository.existsById(id)) {
                submitDTO.setFeedback(feedbackRepository.findById(id).get());
            }
            else submitDTO.setFeedback(null);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Submit Load Success!", submitDTO);
    }
    public ResponseDTO<?> getSubmitsByHomeworkId(int id) {
        List<SubmitDTO> submitDTO = new ArrayList<SubmitDTO>();
        List<SubmitEntity> temp = new ArrayList<SubmitEntity>();
        try {
            temp = submitRepository.findByHomeworkId(id);
            temp.forEach(data -> {
                SubmitDTO tempSubmitDTO = new SubmitDTO();
                tempSubmitDTO.setSubmit(data);
                if(feedbackRepository.existsById(data.getSubmitId())) {
                    tempSubmitDTO.setFeedback(feedbackRepository.findById(data.getSubmitId()).get());
                }
                else {
                    tempSubmitDTO.setFeedback(null);
                }
                submitDTO.add(tempSubmitDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Submit Load Success!", submitDTO);
    }

    public ResponseDTO<?> feedbackSubmit(FeedbackWriteDTO dto) {
        try {
            if(feedbackRepository.existsById(dto.getSubmitId())) {
                FeedbackEntity temp = feedbackRepository.findById(dto.getSubmitId()).get();
                temp.setHwScore(dto.getHwScore());
                temp.setHwComment(dto.getHwComment());
            }
            else {
                FeedbackEntity feedbackEntity = new FeedbackEntity(dto);
                feedbackRepository.save(feedbackEntity);
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Feedback Write Success!", null);
    }

    public ResponseDTO<?> studyLecture(StudyDTO dto) {
        try {
            if(studyRepository.existsByStudentIdAndLectureId(dto.getStudentId(), dto.getLectureId())) {
                StudyEntity temp = studyRepository.findByStudentIdAndLectureId(dto.getStudentId(), dto.getLectureId()).get();
                LectureEntity lecture = lectureRepository.findById(dto.getLectureId()).get();
                if(lecture.getLectureTime() <= dto.getProgressTime()) {
                    temp.setIsStudy(1);
                }
            }
            else {
                StudyEntity studyEntity = new StudyEntity(dto);
                LectureEntity lecture = lectureRepository.findById(dto.getLectureId()).get();
                if(lecture.getLectureTime() <= dto.getProgressTime()) {
                    studyEntity.setIsStudy(1);
                }
                else {
                    studyEntity.setIsStudy(0);
                }
                studyRepository.save(studyEntity);
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Study Success!", null);
    }

    public ResponseDTO<?> getProgressByStudentIdAndSubjectId(int studentId, int subjectId) {
        ProgressDTO dto = new ProgressDTO();
        AtomicInteger count = new AtomicInteger(0);
        try {
            dto.setStudentId(studentId);
            dto.setSubjectId(subjectId);
            dto.setSubjectName(subjectRepository.findById(subjectId).get().getSubjectName());
            List<LectureEntity> lectures = lectureRepository.findBySubjectId(subjectId);
            dto.setNumOfLecture(lectures.size());
            lectures.forEach(data -> {
                if(studyRepository.existsByStudentIdAndLectureId(studentId, data.getLectureId())) {
                    StudyEntity study = studyRepository.findByStudentIdAndLectureId(studentId, data.getLectureId()).get();
                    if(study.getIsStudy() == 1) {
                        count.getAndIncrement();
                    }
                }
            });
            dto.setNumOfStudy(count.get());
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Progress Load Success!", dto);
    }

    public ResponseDTO<?> getProgressByStudentId(int studentId) {
        List<ProgressDTO> progressDTO = new ArrayList<ProgressDTO>();
        try {
            int courseId = studentRepository.findByStudentId(studentId).get().getCourseId();
            List<SubjectEntity> subjects = subjectRepository.findByCourseId(courseId);
            subjects.forEach(data -> {
                ProgressDTO dto = new ProgressDTO();
                AtomicInteger count = new AtomicInteger(0);
                dto.setStudentId(studentId);
                dto.setSubjectId(data.getSubjectId());
                dto.setSubjectName(subjectRepository.findById(data.getSubjectId()).get().getSubjectName());
                List<LectureEntity> lectures = lectureRepository.findBySubjectId(data.getSubjectId());
                dto.setNumOfLecture(lectures.size());
                lectures.forEach(lect -> {
                    if(studyRepository.existsByStudentIdAndLectureId(studentId, lect.getLectureId())) {
                        StudyEntity study = studyRepository.findByStudentIdAndLectureId(studentId, lect.getLectureId()).get();
                        if(study.getIsStudy() == 1) {
                            count.getAndIncrement();
                        }
                    }
                });
                dto.setNumOfStudy(count.get());
                progressDTO.add(dto);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Progress Load Success!", progressDTO);
    }

    public ResponseDTO<?> getSubmitsByStudentId(int id) {
        List<SubmitDTO> submitDTO = new ArrayList<SubmitDTO>();
        List<SubmitEntity> temp = new ArrayList<SubmitEntity>();
        try {
            temp = submitRepository.findByStudentId(id);
            temp.forEach(data -> {
                SubmitDTO tempSubmitDTO = new SubmitDTO();
                tempSubmitDTO.setSubmit(data);
                if(feedbackRepository.existsById(data.getSubmitId())) {
                    tempSubmitDTO.setFeedback(feedbackRepository.findById(data.getSubmitId()).get());
                }
                else tempSubmitDTO.setFeedback(null);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Submits Load Success!", submitDTO);
    }

    public ResponseDTO<?> getStudyByStudentIdAndSubjectId(int studentId, int subjectId) {
        List<StudyEntity> studyEntityList = new ArrayList<StudyEntity>();
        try {
            List<LectureEntity> lectures = lectureRepository.findBySubjectId(subjectId);
            lectures.forEach(data -> {
                if(studyRepository.existsByStudentIdAndLectureId(studentId, data.getLectureId())) {
                    studyEntityList.add(studyRepository.findByStudentIdAndLectureId(studentId, data.getLectureId()).get());
                }
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Study Load Success!", studyEntityList);
    }

    public ResponseDTO<?> getStudyByStudentIdAndLectureId(int studentId, int lectureId) {
        StudyEntity studyEntity = new StudyEntity();
        try {
            if(studyRepository.existsByStudentIdAndLectureId(studentId, lectureId)) {
                studyEntity = studyRepository.findByStudentIdAndLectureId(studentId, lectureId).get();
            }
            else studyEntity = null;
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Study Load Success!", studyEntity);
    }

    public ResponseDTO<?> getSubmitsByStudentIdAndSubjectId(int studentId, int subjectId) {
        List<SubmitDTO> submitDTO = new ArrayList<SubmitDTO>();
        List<SubmitEntity> temp = new ArrayList<SubmitEntity>();
        try {
            List<HomeworkEntity> homeworks = homeworkRepository.findBySubjectIdOrderByStartDateDesc(subjectId);
            homeworks.forEach(data -> {
                if(submitRepository.existsByStudentIdAndHomeworkId(studentId, data.getHomeworkId())) {
                    temp.add(submitRepository.findByStudentIdAndHomeworkId(studentId, data.getHomeworkId()).get());
                }
            });
            temp.forEach(data -> {
                SubmitDTO tempSubmitDTO = new SubmitDTO();
                tempSubmitDTO.setSubmit(data);
                if(feedbackRepository.existsById(data.getSubmitId())) {
                    tempSubmitDTO.setFeedback(feedbackRepository.findById(data.getSubmitId()).get());
                }
                else {
                    tempSubmitDTO.setFeedback(null);
                }
                submitDTO.add(tempSubmitDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Submits Load Success!", submitDTO);
    }

    public ResponseDTO<?> getSubjectByStudentId(int id) {
        List<SubjectDTO> subjects = new ArrayList<SubjectDTO>();
        List<SubjectEntity> temp = new ArrayList<SubjectEntity>();
        try {
            int courseId = studentRepository.findByStudentId(id).get().getCourseId();
            temp = subjectRepository.findByCourseId(courseId);
            temp.forEach(data -> {
                SubjectDTO tempSubjectDTO = new SubjectDTO();
                tempSubjectDTO.setSubject(data);
                tempSubjectDTO.setCourse(courseRepository.findById(courseId).get());
                subjects.add(tempSubjectDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Subjects Load Success!", subjects);
    }

    public ResponseDTO<?> getBoardBySearch(String keyword, int type, int id) {
        List<SubjectBoardEntity> posts = new ArrayList<SubjectBoardEntity>();
        List<SubjectBoardEntity> subjectBoardBySearch = new ArrayList<SubjectBoardEntity>();
        try {
            List<SubjectBoardEntity> subjectBoardBySubjectId = subjectBoardRepository.findBySubjectIdOrderByRegDateDesc(id);
            // 제목으로 검색
            if(type == 1 || type == 0) {
                subjectBoardBySearch = subjectBoardRepository.findByTitleContainingOrderByRegDateDesc(keyword);
                List<SubjectBoardEntity> temp = subjectBoardBySearch.stream().filter(o -> subjectBoardBySubjectId.stream().anyMatch(Predicate.isEqual(o))).collect(Collectors.toList());
                posts.addAll(temp);
            }
            // 내용으로 검색
            if(type == 2 || type == 0) {
                subjectBoardBySearch = subjectBoardRepository.findByContentContainingOrderByRegDateDesc(keyword);
                List<SubjectBoardEntity> temp = subjectBoardBySearch.stream().filter(o -> subjectBoardBySubjectId.stream().anyMatch(Predicate.isEqual(o))).collect(Collectors.toList());
                posts.addAll(temp);
            }
            if(type == 0) {
                Collections.sort(posts, (o1, o2) -> o2.getRegDate().compareTo(o1.getRegDate()));
                Set<SubjectBoardEntity> tempPosts = new HashSet<>(posts);
                List<SubjectBoardEntity> result = new ArrayList<>(tempPosts);
                return ResponseDTO.setSuccess("Searched Subject Board Search Success!", result);
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Searched Subject Board Search Success!", posts);
    }

    public ResponseDTO<?> getLectureBySearch(String keyword, int type, int id) {
        List<LectureEntity> posts = new ArrayList<LectureEntity>();
        List<LectureEntity> lectureBySearch = new ArrayList<LectureEntity>();
        try {
            List<LectureEntity> lectureBySubjectId = lectureRepository.findBySubjectId(id);
            // 제목으로 검색
            if(type == 1 || type == 0) {
                lectureBySearch = lectureRepository.findByTitleContainingOrderByRegDateDesc(keyword);
                List<LectureEntity> temp = lectureBySearch.stream().filter(o -> lectureBySubjectId.stream().anyMatch(Predicate.isEqual(o))).collect(Collectors.toList());
                posts.addAll(temp);
            }
            // 내용으로 검색
            if(type == 2 || type == 0) {
                lectureBySearch = lectureRepository.findByContentContainingOrderByRegDateDesc(keyword);
                List<LectureEntity> temp = lectureBySearch.stream().filter(o -> lectureBySubjectId.stream().anyMatch(Predicate.isEqual(o))).collect(Collectors.toList());
                posts.addAll(temp);
            }
            if(type == 0) {
                Collections.sort(posts, (o1, o2) -> o2.getRegDate().compareTo(o1.getRegDate()));
                Set<LectureEntity> tempPosts = new HashSet<>(posts);
                List<LectureEntity> result = new ArrayList<>(tempPosts);
                return ResponseDTO.setSuccess("Searched Lecture Search Success!", result);
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Searched Lecture Search Success!", posts);
    }

    public ResponseDTO<?> getQnaBySearch(String keyword, int type, int id) {
        List<SubjectQnaDTO> posts = new ArrayList<SubjectQnaDTO>();
        List<SubjectQuestionEntity> subjectQuestionBySearch = new ArrayList<SubjectQuestionEntity>();
        try {
            List<SubjectQuestionEntity> subjectQuestionBySubjectId = subjectQuestionRepository.findBySubjectIdOrderByRegDateDesc(id);
            // 제목으로 검색
            if(type == 1 || type == 0) {
                subjectQuestionBySearch = subjectQuestionRepository.findByTitleContainingOrderByRegDateDesc(keyword);
                List<SubjectQuestionEntity> temp = subjectQuestionBySearch.stream().filter(o -> subjectQuestionBySubjectId.stream().anyMatch(Predicate.isEqual(o))).collect(Collectors.toList());
                temp.forEach(data -> {
                    SubjectQnaDTO tempSubjectQnaDTO = new SubjectQnaDTO();
                    tempSubjectQnaDTO.setQuestion(data);
                    if(subjectAnswerRepository.existsById(data.getSubjectQuestionId())) {
                        tempSubjectQnaDTO.setAnswer(subjectAnswerRepository.findById(data.getSubjectQuestionId()).get());
                    }
                    else tempSubjectQnaDTO.setAnswer(null);
                    posts.add(tempSubjectQnaDTO);
                });
            }
            // 내용으로 검색
            if(type == 2 || type == 0) {
                subjectQuestionBySearch = subjectQuestionRepository.findByContentContainingOrderByRegDateDesc(keyword);
                List<SubjectQuestionEntity> temp = subjectQuestionBySearch.stream().filter(o -> subjectQuestionBySubjectId.stream().anyMatch(Predicate.isEqual(o))).collect(Collectors.toList());
                temp.forEach(data -> {
                    SubjectQnaDTO tempSubjectQnaDTO = new SubjectQnaDTO();
                    tempSubjectQnaDTO.setQuestion(data);
                    if(subjectAnswerRepository.existsById(data.getSubjectQuestionId())) {
                        tempSubjectQnaDTO.setAnswer(subjectAnswerRepository.findById(data.getSubjectQuestionId()).get());
                    }
                    else tempSubjectQnaDTO.setAnswer(null);
                    posts.add(tempSubjectQnaDTO);
                });
            }
            // 작성자로 검색
            if(type == 3 || type == 0) {
                List<UserEntity> tempUser = userRepository.findByUserNameContaining(keyword);
                List<StudentDTO> tempStudent = new ArrayList<StudentDTO>();
                if(!tempUser.isEmpty()) {
                    tempUser.forEach(data -> {
                        StudentDTO tempStudentDTO = new StudentDTO();
                        if(studentRepository.existsByUid(data.getUid())) {
                            tempStudentDTO.setUser(data);
                            tempStudentDTO.setStudent(studentRepository.findByUid(data.getUid()).get());
                            tempStudent.add(tempStudentDTO);
                        }
                    });
                }
                if(!tempStudent.isEmpty()) {
                    tempStudent.forEach(data -> {
                        SubjectQnaDTO tempSubjectQnaDTO = new SubjectQnaDTO();
                        List<SubjectQuestionEntity> tempQuestion = new ArrayList<SubjectQuestionEntity>();
                        tempQuestion = subjectQuestionRepository.findByStudentIdOrderByRegDateDesc(data.getStudent().getStudentId());

                        List<SubjectQuestionEntity> temp = tempQuestion.stream().filter(o -> subjectQuestionBySubjectId.stream().anyMatch(Predicate.isEqual(o))).collect(Collectors.toList());
                        temp.forEach(ques -> {
                            tempSubjectQnaDTO.setQuestion(ques);
                            if(subjectAnswerRepository.existsById(ques.getSubjectQuestionId())) {
                                tempSubjectQnaDTO.setAnswer(subjectAnswerRepository.findById(ques.getSubjectQuestionId()).get());
                            }
                            else tempSubjectQnaDTO.setAnswer(null);
                            posts.add(tempSubjectQnaDTO);
                        });
                    });
                }
            }
            if(type == 0) {
                Collections.sort(posts, (o1, o2) -> o2.getQuestion().getRegDate().compareTo(o1.getQuestion().getRegDate()));
                Set<SubjectQnaDTO> tempPosts = new HashSet<>(posts);
                List<SubjectQnaDTO> result = new ArrayList<>(tempPosts);
                return ResponseDTO.setSuccess("Searched Subject QnA Search Success!", result);
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Searched Subject QnA Search Success!", posts);
    }

    public ResponseDTO<?> writeSubjectQuestion(SubjectQuestionWriteDTO dto) {
        SubjectQuestionEntity subjectQuestionEntity = new SubjectQuestionEntity(dto);
        try {
            subjectQuestionRepository.save(subjectQuestionEntity);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Write Subject Question Success!", null);
    }

    public ResponseDTO<?> writeSubjectQuestion(int id, SubjectQuestionWriteDTO dto) {
        try {
            SubjectQuestionEntity subjectQuestionEntity = subjectQuestionRepository.findById(id).get();
            subjectQuestionEntity.setTitle(dto.getTitle());
            subjectQuestionEntity.setContent(dto.getContent());
            subjectQuestionRepository.save(subjectQuestionEntity);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Write Subject Question Success!", null);
    }

    public ResponseDTO<?> getSubmitByStudentIdAndHomeworkId(int studentId, int homeworkId) {
        SubmitDTO submitDTO = new SubmitDTO();
        try {
            if(submitRepository.existsByStudentIdAndHomeworkId(studentId, homeworkId)){
                SubmitEntity submitEntity = submitRepository.findByStudentIdAndHomeworkId(studentId, homeworkId).get();
                submitDTO.setSubmit(submitEntity);
                if(feedbackRepository.existsById(submitEntity.getSubmitId())) {
                    submitDTO.setFeedback(feedbackRepository.findById(submitEntity.getSubmitId()).get());
                }
                else {
                    submitDTO.setFeedback(null);
                }
            }
            else {
                return ResponseDTO.setFailed("Not Exist Data");
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Submit Load Success!", submitDTO);
    }
}
