package com.lti.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.lti.entity.Question;
import com.lti.entity.Subject;

@Repository
@Transactional
public class QuestionRepoImpl extends GenericRepoImpl implements QuestionRepo {

	@Override
	public void save(Question question) {
		entityManager.merge(question);

	}

	@Override
	public List fetchQuestionsBySubject(int subjectId) {
		String jpql = "select q from Question q where q.subject.id = :subject_id order by q.level";
		List list = entityManager.createQuery(jpql).setParameter("subject_id", subjectId).getResultList();
		return list;

	}

	@Override
	public List fetchQuestionBySubjectAndLevel(int subjectId, int level) {
		String jpql = "select q from Question q where q.subject.id = :subject_id and q.level = :level";
		List list = entityManager.createQuery(jpql).setParameter("subject_id", subjectId).setParameter("level", level)
				.setMaxResults(10).getResultList();
		return list;

	}

	@Override
	public boolean addQuestionFromCsv(String targetFile, Subject subject) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(targetFile));
		try {

			br.readLine();
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				if (row.length > 0) {
					Question newQuestion = new Question();

					newQuestion.setQues(row[0]);
					newQuestion.setOptionA(row[1]);
					newQuestion.setOptionB(row[2]);
					newQuestion.setOptionC(row[3]);
					newQuestion.setOptionD(row[4]);
					newQuestion.setCorrectAns(row[5]);
					newQuestion.setLevel(Integer.parseInt(row[6]));
					newQuestion.setSubject(subject);
					entityManager.merge(newQuestion);

				} else {
					System.out.println("No Data Found");
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public double noOfQuestion(int sId) {
		String jpql = "select q from Question q where q.subject.id =:sId and q.level =:lvl";
		double total = 0;
		for (int i = 1; i <= 3; i++) {
			List<Question> list = entityManager.createQuery(jpql).setParameter("sId", sId).setParameter("lvl", i)
					.getResultList();
			total = total + list.size();
			System.out.println("total=" + total);
		}
		return total / 3;
	}

}
