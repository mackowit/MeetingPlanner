package com.crud.planner.service;

import com.crud.planner.domain.Group;
import com.crud.planner.exception.GroupNotFoundException;
import com.crud.planner.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group saveGroup(final Group group) {
        return groupRepository.save(group);
    }

    public Optional<Group> getGroupById(final Long id) {
        return groupRepository.findById(id);
    }

    public void deleteGroup(final Long id) {
        groupRepository.deleteById(id);
    }

    public Group updateGroup(final Group groupAfterChange) {
        if (groupRepository.existsById(groupAfterChange.getId())) {
            return groupRepository.save(groupAfterChange);
        } else {
            throw new GroupNotFoundException();
        }
    }
}
