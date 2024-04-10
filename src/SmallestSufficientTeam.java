package src;

import java.util.*;

public class SmallestSufficientTeam {

    private static class Input {

        String[] req_skills;
        List<List<String>> people;

        public Input(String[] req_skills, List<List<String>> people) {
            this.req_skills = req_skills;
            this.people = people;
        }

        public String[] getReq_skills() {
            return req_skills;
        }

        public void setReq_skills(String[] req_skills) {
            this.req_skills = req_skills;
        }

        public List<List<String>> getPeople() {
            return people;
        }

        public void setPeople(List<List<String>> people) {
            this.people = people;
        }
    }

    public static void main(String[] args) {
        List<Input> inputs = new ArrayList<>();
        inputs.add(new Input(new String[]{"java", "nodejs", "reactjs"},
                new ArrayList<>(Arrays.asList(
                        Collections.singletonList("java"),
                        Collections.singletonList("nodejs"),
                        Arrays.asList("nodejs", "reactjs")
                ))
        ));
        inputs.add(new Input(new String[]{"algorithms", "math", "java", "reactjs", "csharp", "aws"},
                new ArrayList<>(Arrays.asList(
                        Arrays.asList("algorithms", "math", "java"),
                        Arrays.asList("algorithms", "math", "reactjs"),
                        Arrays.asList("java", "csharp", "aws"),
                        Arrays.asList("reactjs", "csharp"),
                        Arrays.asList("csharp", "math"),
                        Arrays.asList("aws", "java")
                ))
        ));
        for (int i = 0; i < inputs.size(); i++) {
            tester(i + 1, inputs.get(i));
        }
    }

    private static void tester(int num, Input input) {
        SmallestSufficientTeam test = new SmallestSufficientTeam();
        System.out.println("TestNum:" + num);
        System.out.println("Answer:" + Arrays.toString(test.smallestSufficientTeam(input.req_skills, input.people)));
        System.out.println();
    }

    //incomplete
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int[] bitPeople = convertToPeopleSkillBitMap(req_skills, people);
        int reqSkillsBit = 0;
        for (int i = 0; i < req_skills.length; i++) {
            reqSkillsBit = reqSkillsBit | 1 << i;
        }
        return null;
    }

    private int[] convertToPeopleSkillBitMap(String[] req_skills, List<List<String>> people) {
        int[] result = new int[people.size()];
        for (int i = 0; i < people.size(); i++) {
            int bitSkill = 0;
            Set skills = new HashSet(people.get(i));
            for (int j = 0; j < req_skills.length; j++) {
                if (skills.contains(req_skills[j])) {
                    bitSkill = bitSkill | 1 << j;
                }
            }
            result[i] = bitSkill;
        }
        return result;
    }

    public List<Integer> smallestSufficientTeam(int reqSkillBit, int[] peopleSkill, int tryPersonIndex, List<Integer> selectedPeople) {
        if (tryPersonIndex >= peopleSkill.length) {
            return null;
        } else {
            int skillSet = buildSkillSet(selectedPeople, peopleSkill);
            if (reqSkillBit == skillSet) {
                return selectedPeople;
            }
            List<Integer> unSelectPerson = smallestSufficientTeam(reqSkillBit, peopleSkill, tryPersonIndex+1, new ArrayList<>(selectedPeople));
            List<Integer> selectPersonList = new ArrayList<>();
            selectPersonList.addAll(selectedPeople);
            selectPersonList.add(tryPersonIndex);
            List<Integer> selectPerson = smallestSufficientTeam(reqSkillBit, peopleSkill, tryPersonIndex+1, selectPersonList);
            
        }
        return null;
    }

    private int buildSkillSet(List<Integer> selectedPeople, int[] peopleSkill) {
        int skill = 0;
        for (int person : selectedPeople) {
            skill = skill | peopleSkill[person];
        }
        return skill;
    }

}
