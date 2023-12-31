package com.ac.yy.Service;

import com.ac.yy.DTO.*;
import com.ac.yy.Entity.*;
import com.ac.yy.Repository.*;
import javafx.scene.effect.SepiaTone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
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
    @Autowired CourseReviewRepository courseReviewRepository;
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
        SubmitEntity result = null;
        try {
            if(submitRepository.existsByStudentIdAndHomeworkId(submit.getStudentId(), submit.getHomeworkId())) {
                SubmitEntity temp = submitRepository.findByStudentIdAndHomeworkId(submit.getStudentId(), submit.getHomeworkId()).get();
                temp.setSubmitContent(submit.getSubmitContent());
                temp.setSubmitFileUrl(submit.getSubmitFileUrl());
                temp.setSubmitFileName(submit.getSubmitFileName());
                result = submitRepository.save(temp);
            }
            else {
                result = submitRepository.save(submit);
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Submit Success!", result);
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

    public ResponseDTO<?> writeFeedback(int id, FeedbackWriteDTO dto) {
        try {
            if(feedbackRepository.existsById(id)) {
                FeedbackEntity temp = feedbackRepository.findById(id).get();
                temp.setHwScore(dto.getHwScore());
                temp.setHwComment(dto.getHwComment());
                feedbackRepository.save(temp);
            }
            else {
                FeedbackEntity feedbackEntity = new FeedbackEntity(dto);
                feedbackEntity.setSubmitId(id);
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
                studyRepository.modifyingProgressTimebyStudyId(temp.getStudyId(), dto.getProgressTime());
                if(lecture.getLectureTime() <= dto.getProgressTime()) {
                    temp.setIsStudy(1);
                    studyRepository.modifyingIsStudybyStudyId(temp.getLectureId(), temp.getIsStudy());
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

    public ResponseDTO<?> getStudyBySubjectId(int subjectId) {
        List<StudyEntity> studyEntityList = new ArrayList<StudyEntity>();
        try {
            List<LectureEntity> lectures = lectureRepository.findBySubjectId(subjectId);
            lectures.forEach(data -> {
                studyEntityList.addAll(studyRepository.findByLectureId(data.getLectureId()));
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Study Load Success!", studyEntityList);
    }

    public ResponseDTO<?> getStudyByLectureId(int lectureId) {
        List<StudyEntity> studyEntityList;
        try {
            studyEntityList = new ArrayList<StudyEntity>(studyRepository.findByLectureId(lectureId));
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Study Load Success!", studyEntityList);
    }

    public ResponseDTO<?> getSubmitsBySubjectId(int subjectId) {
        List<SubmitDTO> submitDTO = new ArrayList<SubmitDTO>();
        List<SubmitEntity> temp = new ArrayList<SubmitEntity>();
        try {
            List<HomeworkEntity> homeworks = homeworkRepository.findBySubjectIdOrderByStartDateDesc(subjectId);
            homeworks.forEach(data -> {
                temp.addAll(submitRepository.findByHomeworkId(data.getHomeworkId()));
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
        SubjectQuestionEntity result = null;
        try {
            result = subjectQuestionRepository.save(subjectQuestionEntity);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Write Subject Question Success!", result);
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

    public ResponseDTO<?> getSubjectReviewBySubjectId(int id) {
        List<CourseReviewEntity> reviews = new ArrayList<CourseReviewEntity>();
        try {
            reviews = courseReviewRepository.findBySubjectIdOrderByReviewScoreDesc(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Subject Reviews in Course Load Success!", reviews);
    }

    public ResponseDTO<?> add(List<SubjectAddDTO> subjects, int id) {
        List<SubjectEntity> subjectEntity = new ArrayList<SubjectEntity>();
        try {
            subjects.forEach(sub -> {
                CourseEntity course = courseRepository.findById(id).get();
                SubjectEntity subject = new SubjectEntity();
                subject.setCourseId(course.getCourseId());
                subject.setSubjectName(sub.getSubjectName());
                subject.setAcademicId(sub.getAcademicId());
                subjectEntity.add(subject);
            });
            System.out.println(subjectEntity);
            subjectRepository.saveAll(subjectEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Subject Add Success!", subjects);
    }

    public ResponseDTO<?> writeBoard(SubjectBoardWriteDTO dto) {
        SubjectBoardEntity result = null;
        try {
            SubjectBoardEntity post = new SubjectBoardEntity(dto);
            result = subjectBoardRepository.save(post);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Write Subject Board Success!", result);
    }

    public ResponseDTO<?> writeBoard(int id, SubjectBoardWriteDTO dto) {
        try {
            if(subjectBoardRepository.existsById(id)) {
                SubjectBoardEntity post = subjectBoardRepository.findById(id).get();
                post.setTitle(dto.getTitle());
                post.setContent(dto.getContent());
                subjectBoardRepository.save(post);
            }
            else {
                return ResponseDTO.setFailed("It doesn't exist post.");
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Modify Subject Board Success!", null);
    }

    public ResponseDTO<?> writeReply(int id, ReplyDTO dto) {
        SubjectAnswerEntity reply = null;
        try {
            if(subjectAnswerRepository.existsById(id)) {
                reply = subjectAnswerRepository.findById(id).get();
                reply.setAnswerContent(dto.getContent());
                subjectAnswerRepository.save(reply);
            }
            else {
                reply = new SubjectAnswerEntity(dto);
                reply.setSubjectQuestionId(id);
                subjectAnswerRepository.save(reply);
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Write Reply Success!", null);
    }


    public ResponseDTO<?> mod(List<SubjectAddDTO> subjects, int id, int check) {
        List<SubjectEntity> subjectEntity = new ArrayList<SubjectEntity>();
        try {
            if (check == 0) {
                List<SubjectEntity> temp = subjectRepository.findByCourseId(id);
                ListIterator<SubjectEntity> it = temp.listIterator();
                while (it.hasNext()) {
                    subjectRepository.deleteById(it.next().getSubjectId());
                }
                subjects.forEach(sub -> {
                    CourseEntity course = courseRepository.findById(id).get();
                    SubjectEntity subject = new SubjectEntity();
                    subject.setCourseId(course.getCourseId());
                    subject.setSubjectName(sub.getSubjectName());
                    subject.setAcademicId(sub.getAcademicId());
                    subjectEntity.add(subject);
                });
                System.out.println(subjectEntity);
                subjectRepository.saveAll(subjectEntity);
            } else if (check == 1) {
                CourseEntity course = courseRepository.findById(id).get();
                ListIterator<SubjectAddDTO> sub = subjects.listIterator();
                List<SubjectEntity> temp = subjectRepository.findByCourseId(id);
                SubjectEntity subject = new SubjectEntity();
                for (int i = 0; i < subjects.size(); i++) {
                    if (i >= temp.size()) {
                        subject.setCourseId(course.getCourseId());
                        subject.setSubjectName(subjects.get(i).getSubjectName());
                        subject.setAcademicId(subjects.get(i).getAcademicId());
                        subjectEntity.add(subject);
                    } else {
                        subjectRepository.modifyingSubjectBySubjectId(temp.get(i).getSubjectId(), subjects.get(i).getSubjectName(), subjects.get(i).getAcademicId());
                    }
                }
                ;
                subjectRepository.saveAll(subjectEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Subject Add Success!", subjects);
    }

    public ResponseDTO<?> writeLecture(int subjectId, LectureDTO dto) {
        LectureEntity result = null;
        try {
            LectureEntity lecture = new LectureEntity(dto);
            lecture.setSubjectId(subjectId);
            result = lectureRepository.save(lecture);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Lecture Write Success!", result);
    }

    public ResponseDTO<?> writeHomework(HomeworkDTO dto) {
        HomeworkEntity result = null;
        try {
            HomeworkEntity homework = new HomeworkEntity(dto);
            result = homeworkRepository.save(homework);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Homework Write Success!", result);
    }

    public ResponseDTO<?> writeHomework(int id, HomeworkDTO dto) {
        try {
            HomeworkEntity homework = homeworkRepository.findById(id).get();
            homework.setTitle(dto.getTitle());
            homework.setContent(dto.getContent());
            homework.setStartDate(dto.getStartDate());
            homework.setEndDate(dto.getEndDate());
            homeworkRepository.save(homework);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Homework Write Success!", null);
    }

    public ResponseDTO<?> modLecture(int lectureId, LectureDTO dto) {
        try {
            lectureRepository.modifyingInfoByLectureId(lectureId, dto.getTitle(), dto.getContent(), dto.getVideoTime());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Lecture Mod Success!", null);
    }

    public ResponseDTO<?> deleteBoard(int id) {
        try {
            subjectBoardRepository.deleteById(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Delete Board Success!", null);
    }

    public ResponseDTO<?> deleteLecture(int id) {
        try {
            lectureRepository.deleteById(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Delete Lecture Success!", null);
    }

    public ResponseDTO<?> deleteHomework(int id) {
        try {
            if(submitRepository.existsByHomeworkId(id)) {
                List<SubmitEntity> submit = submitRepository.findByHomeworkId(id);
                List<FeedbackEntity> feedback = new ArrayList<FeedbackEntity>();
                submit.forEach(data -> {
                    if(feedbackRepository.existsById(data.getSubmitId())) {
                        feedback.add(feedbackRepository.findById(data.getSubmitId()).get());
                    }
                });
                submitRepository.deleteAllInBatch(submit);
                if(!feedback.isEmpty()) {
                    feedbackRepository.deleteAllInBatch(feedback);
                }
            }

            homeworkRepository.deleteById(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Delete Homework Success!", null);
    }

    public ResponseDTO<?> deleteReply(int id) {
        try {
            subjectAnswerRepository.deleteById(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Delete Reply Success!", null);
    }

    public ResponseDTO<?> deleteQuestion(int id) {
        try {
            if(subjectAnswerRepository.existsById(id)) {
                subjectAnswerRepository.deleteById(id);
            }
            subjectQuestionRepository.deleteById(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Delete Question Success!", null);
    }

    public ResponseDTO<?> deleteFeedback(int id) {
        try {
            feedbackRepository.deleteById(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Delete Feedback Success!", null);
    }
}
