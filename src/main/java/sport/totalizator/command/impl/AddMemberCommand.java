package sport.totalizator.command.impl;

import org.apache.log4j.Logger;
import sport.totalizator.command.CommandEnum;
import sport.totalizator.command.ICommand;
import sport.totalizator.command.exception.CommandException;
import sport.totalizator.command.factory.CommandFactory;
import sport.totalizator.entity.User;
import sport.totalizator.exception.MemberException;
import sport.totalizator.exception.UnauthorizedException;
import sport.totalizator.service.LeagueService;
import sport.totalizator.service.MemberService;
import sport.totalizator.service.exception.ServiceException;
import sport.totalizator.service.factory.ServiceFactory;
import sport.totalizator.util.MessageLocalizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static sport.totalizator.entity.User.Role.MODERATOR;

public class AddMemberCommand implements ICommand {
    private static final Logger log = Logger.getLogger(AddMemberCommand.class);
    private final MemberService memberService = ServiceFactory.getInstance().getMemberService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        checkRoots(req, new User.Role[]{MODERATOR});
        try {
            memberService.addMember((String)req.getParameter("name"),
                    (String)req.getParameter("category-id"), (String)req.getParameter("league-id"));
        }
        catch(ServiceException exc){
            log.error(exc);
            throw new CommandException(exc);
        }
        catch (MemberException exc){
            log.error(exc);
            req.setAttribute("error", MessageLocalizer.getLocalizedForCurrentLocaleMessage(exc.getErrorMessageList(), req));
            req.setAttribute("member", exc.getMember());
            CommandFactory.getFactory().createCommand(CommandEnum.SHOW_ADD_MEMBER_PAGE).execute(req, resp);
        }
        req.setAttribute("success", MessageLocalizer.getLocalizedForCurrentLocaleMessage("success.member-is-added", req));
        CommandFactory.getFactory().createCommand(CommandEnum.SHOW_ADD_MEMBER_PAGE).execute(req, resp);
    }
}
