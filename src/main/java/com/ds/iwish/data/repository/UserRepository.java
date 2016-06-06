package com.ds.iwish.data.repository;

import com.ds.iwish.bean.Category;
import com.ds.iwish.bean.Layout;
import com.ds.iwish.bean.Profile;
import com.ds.iwish.bean.Template;
import com.ds.iwish.data.dao.UserDAO;
import com.ds.iwish.helper.ProfileHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepository {

    public static final Logger LOG = LogManager.getLogger(UserRepository.class.getName());


    private UserDAO userDAO;
    private LayoutRepository layoutRepository;
    private TemplateRepository templateRepository;
    private CategoryRepository categoryRepository;


    public Profile getUserById(long pId){
        LOG.info("Inside #getUserById : (pId = {})", pId);
        return getUserDAO().getUserById(pId);
    }

    public Profile getUserByEmail(String pEmail){
        LOG.info("Inside #getUserByEmail : (pEmail = {})", pEmail);
        Profile Profile = getUserDAO().getUserByEmail(pEmail);
        LOG.info("Leaving #getUserByEmail : (profile = {})", Profile);
        return Profile;
    }

    public List<Profile> getUsersByKeyword(String keyword){
        LOG.info("Inside #getUsersByKeyword : (keyword = {})", keyword);
        String [] names = keyword.split(" ");
        String firstName = names[0];
        String lastName;
        if(names.length < 2) {
            lastName = names[0];
        } else {
            lastName = names[1];
        }
        long userId = ProfileHelper.getProfileFromSession().getId();
        return getUserDAO().getUsersByName(firstName, lastName, userId);
    }

    public void addFriend(long userId, long otherUserId) {
        LOG.info("Inside #addFriend : (userId = {})", userId);
        getUserDAO().createFriendship(userId, otherUserId);
    }

    public List<Profile> getUnapprovedFriends(long profileId) {
        LOG.info("Inside #getUnapprovedFriends : (profileId = {})", profileId);
        return getUserDAO().getUnapprovedFriends(profileId);
    }

    public void approveFriend(long userId, long otherUserId) {
        LOG.info("Inside #approveFriend : (userId = {})", userId);
        getUserDAO().updateFriendship(userId, otherUserId, true);
    }

    public void rejectFriend(long userId, long otherUserId) {
        LOG.info("Inside #rejectFriend : (userId = {})", userId);
        getUserDAO().updateFriendship(userId, otherUserId, false);
    }

    public List<Profile> getFriends(long userId) {
        LOG.info("Inside #getFriends : (userId = {})", userId);
        return getUserDAO().getFriendUsers(userId);
    }

    public Profile createUser(Profile pProfile){
        LOG.info("Inside #createUser : (pProfile = {})", pProfile);
        Profile profile = getUserDAO().createUser(pProfile);
        LOG.info("Leaving #createUser : (profile = {})", profile);
        return profile;
    }

    public void assignDefaultPresets(Profile profile) {
        LOG.info("Inside #assignDefaultPresets : (profile = {})", profile);
        Layout layout = copyDefaultLayout(profile.getId());
        Template template = copyDefaultTemplate(profile.getId());
        Category category = copyDefaultCategory(profile.getId(), layout.getLayoutId());

        profile.setDefaultLayout(layout.getLayoutId());
        profile.setDefaultTemplate(template.getTemplateId());
        profile.setDefaultCategory(category.getCategoryId());

        getUserDAO().updateDefaults(profile);
    }

    private Layout copyDefaultLayout(long userId) {
        LOG.info("Inside #copyDefaultLayout : (userId = {})", userId);
        Layout layout = getLayoutRepository().getLayoutById(0);
        Layout newLayout = new Layout();
        newLayout.setTitle(layout.getTitle());
        newLayout.setModel(layout.getModel());
        newLayout.setWidth(layout.getWidth());
        newLayout.setHeight(layout.getHeight());
        newLayout.setPadding(layout.getPadding());
        newLayout.setMargin(layout.getMargin());
        newLayout.setUserId(userId);

        return getLayoutRepository().createLayout(newLayout);
    }

    private Template copyDefaultTemplate(long userId) {
        LOG.info("Inside #copyDefaultTemplate : (userId = {})", userId);
        Template template = getTemplateRepository().getTemplateById(0);
        Template newTemplate = new Template();
        newTemplate.setTitle(template.getTitle());
        newTemplate.setMainColor(template.getMainColor());
        newTemplate.setTitleColor(template.getTitleColor());
        newTemplate.setTextColor(template.getTextColor());
        newTemplate.setBorderColor(template.getBorderColor());
        newTemplate.setBorderType(template.getBorderType());
        newTemplate.setBorderWidth(template.getBorderWidth());
        newTemplate.setTitleStyle(template.getTitleStyle());
        newTemplate.setTitleFont(template.getTitleFont());
        newTemplate.setTextStyle(template.getTextStyle());
        newTemplate.setTextFont(template.getTextFont());
        newTemplate.setImagePosition(template.getImagePosition());
        newTemplate.setUserId(userId);

        return getTemplateRepository().createTemplate(newTemplate);
    }

    private Category copyDefaultCategory(long userId, long layoutId) {
        LOG.info("Inside #copyDefaultCategory : (userId = {})", userId);
        Category category = getCategoryRepository().getCategoryById(0);
        Category newCategory = new Category();
        newCategory.setTitle(category.getTitle());
        newCategory.setPriority(category.getPriority());
        newCategory.setBackground(category.getBackground());
        newCategory.setColor(category.getColor());
        newCategory.setLayoutId(layoutId);
        newCategory.setUserId(userId);

        return getCategoryRepository().createCategory(newCategory);
    }

    public boolean removeFriend(long profileId, long otherProfileId){
        LOG.info("Inside #removeFriend : (profileId = {})", profileId);
        return getUserDAO().deleteFriendship(profileId, otherProfileId);
    }


    public UserDAO getUserDAO() {
        return userDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public LayoutRepository getLayoutRepository() {
        return layoutRepository;
    }

    @Autowired
    public void setLayoutRepository(LayoutRepository layoutRepository) {
        this.layoutRepository = layoutRepository;
    }

    public TemplateRepository getTemplateRepository() {
        return templateRepository;
    }

    @Autowired
    public void setTemplateRepository(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
