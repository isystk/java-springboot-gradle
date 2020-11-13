package com.isystk.sample.common.helper;

import lombok.val;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.Map;

import static com.isystk.sample.common.util.ValidateUtils.isNotEmpty;

/**
 * メール送信ヘルパー
 */
@Component
public class SendMailHelper {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SendMailHelper.class);
    @Autowired
	JavaMailSender javaMailSender;

	/**
	 * メールを送信します。
	 *
	 * @param fromAddress
	 * @param toAddress
	 * @param subject
	 * @param body
	 */
	public void sendMail(String fromAddress, String[] toAddress, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromAddress);
		message.setTo(toAddress);
		message.setSubject(subject);
		message.setText(body);

		try {
			javaMailSender.send(message);
		} catch (MailException e) {
			log.error("failed to send mail.", e);
			throw e;
		}
	}

	/**
	 * 指定したテンプレートのメール本文を返します。
	 *
	 * @param template
	 * @param objects
	 * @return
	 */
	public String getMailBody(String template, Map<String, Object> objects) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());

		Context context = new Context();
		if (isNotEmpty(objects)) {
			objects.forEach(context::setVariable);
		}

		return templateEngine.process(template, context);
	}

	private ITemplateResolver templateResolver() {
		StringTemplateResolver resolver = new StringTemplateResolver();
		resolver.setTemplateMode("TEXT");
		resolver.setCacheable(false); // 安全をとってキャッシュしない
		return resolver;
	}
}
